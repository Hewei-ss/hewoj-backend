package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.hewoj.exception.BusinessException;
import com.yupi.hewoj.mapper.VoucherOrderMapper;
import com.yupi.hewoj.model.entity.SeckillVoucher;
import com.yupi.hewoj.model.entity.User;
import com.yupi.hewoj.model.entity.VoucherOrder;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.service.SeckillVoucherService;
import com.yupi.hewoj.service.VoucherOrderService;
import com.yupi.hewoj.service.VoucherService;
import com.yupi.hewoj.utils.RedisIdWorker;
import com.yupi.hewoj.utils.distributedlock.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.concurrent.*;

import static com.yupi.hewoj.constant.RedisContant.VOUCHER_ORDER;
import static com.yupi.hewoj.constant.RedisContant.VOUCHER_ORDER_LOCK;
import static com.yupi.hewoj.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 31695
 * @description 针对表【voucher_order(抢购优惠卷生成的订单)】的数据库操作Service实现
 * @createDate 2024-05-16 20:55:56
 */
@Service
@Slf4j
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder>
        implements VoucherOrderService {

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private SeckillVoucherService seckillVoucherService;

    @Resource
    private DistributedLock distributedLock;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;

    //在静态代码框中初始化UNLOCK_SCRIPT
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("seckill.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    //异步处理线程池
    private static final ExecutorService SECKILL_ORDER_EXECUTOR = Executors.newSingleThreadExecutor();
    private static final ExecutorService SECKILL_ORDER_EXECUTOR_2=Executors.newFixedThreadPool(10);

    private class VoucherOrderHandler implements Runnable{
        private VoucherOrder voucherOrder;
        VoucherOrderHandler(VoucherOrder voucherOrder){
            this.voucherOrder=voucherOrder;
        }
        @Override
        public void run() {
            handleVoucherOrder(voucherOrder);
        }
    }
    //阻塞队列，当一个线程尝试从 阻塞队列中获取任务时，如果队列中没有任务，则该线程就会阻塞直到队列中有任务该线程才会被唤醒
    private BlockingQueue<VoucherOrder> orderTasks = new ArrayBlockingQueue<>(1024 * 1024);

    //在类初始化之后执行，因为当这个类初始化好了之后，随时都是有可能要执行的
//    @PostConstruct
//    private void init() {
//        SECKILL_ORDER_EXECUTOR.submit(new VoucherOrderHandler());
//    }
    // 用于线程池处理的任务
// 当初始化完毕后，就会去从对列中去拿信息
//    private class VoucherOrderHandler implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                try {
//                    // 1.获取队列中的订单信息
//                    VoucherOrder voucherOrder = orderTasks.take();
//                    // 2.创建订单
//                    handleVoucherOrder(voucherOrder);
//                } catch (Exception e) {
//                    log.error("处理订单异常", e);
//                }
//            }
//        }
//    }

    /**
     * 创建订单
     */
    private void handleVoucherOrder(VoucherOrder voucherOrder) {
        //1.获取用户
        Long userId = voucherOrder.getUserId();
        Long voucherId=voucherOrder.getVoucherId();
        // 2.创建锁对象
        RLock lock = redissonClient.getLock(VOUCHER_ORDER_LOCK+voucherId+":"+userId);
        // 3.尝试获取锁
        boolean isLock = lock.tryLock();
        // 4.判断是否获得锁成功
        if (!isLock) {
            // 获取锁失败，直接返回失败或者重试
            log.error("不允许重复下单！");
            return;
        }
        try {
            //注意：由于是spring的事务是放在threadLocal中，此时的是多线程，事务会失效
            proxy.createVoucherOrder(voucherOrder);
        } finally {
            // 释放锁
            lock.unlock();
        }
    }


    private VoucherOrderService proxy;


    @Override
    public long  seckillVoucher(Long voucherId, HttpServletRequest httpServletRequest) {



        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        Long userId = user.getId();
        //执行lua脚本
        Long res = stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(), userId.toString());

        if (res == 1) throw new BusinessException(ResponseCodeEnum.OPERATION_ERROR, "库存不足");
        if (res == 2) throw new BusinessException(ResponseCodeEnum.OPERATION_ERROR, "不能重复下单");
        //生成订单id
        //方便统计每天的订单量
        String statisticalUnit = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        long orderId = redisIdWorker.nextId(VOUCHER_ORDER + voucherId, statisticalUnit);
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setId(orderId);
        voucherOrder.setUserId(userId);
        voucherOrder.setVoucherId(voucherId);
        //将订单信息提交到阻塞队列中
//        orderTasks.add(voucherOrder);
        SECKILL_ORDER_EXECUTOR_2.submit(new VoucherOrderHandler(voucherOrder));

        proxy = (VoucherOrderService)AopContext.currentProxy();
        return orderId;
    }
    @Override
    @Transactional
    public  void createVoucherOrder(VoucherOrder voucherOrder) {
        Long userId = voucherOrder.getUserId();
        // 5.1.查询订单
        int count = query().eq("userId", userId).eq("voucherId", voucherOrder.getVoucherId()).count().intValue();
        //int num = this.query().eq("userId", userId).eq("voucherId", voucherId).count().intValue();
        // 5.2.判断是否存在
        if (count > 0) {
            // 用户已经购买过了
            log.error("用户已经购买过了");
            return ;
        }

        // 6.扣减库存
        boolean success = seckillVoucherService.update()
                .setSql("stock = stock - 1") // set stock = stock - 1
                .eq("voucher_id", voucherOrder.getVoucherId()).gt("stock", 0) // where id = ? and stock > 0
                .update();
        if (!success) {
            // 扣减失败
            log.error("库存不足");
            return ;
        }
        save(voucherOrder);

    }

    /**
     * 秒杀优惠卷
     *
     * @param voucherId
     * @param httpServletRequest
     * @return
     */
//    @Override
//    public long seckillVoucher(Long voucherId, HttpServletRequest httpServletRequest) {
//        //查询优惠卷信息
//        SeckillVoucher seckillVoucher = seckillVoucherService.getById(voucherId);
//        //判断是否在优惠时间范围内
//        if (LocalDateTime.now().isBefore(seckillVoucher.getBegin_time())) {
//            throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR, "不在优惠期间");
//        }
//        if (LocalDateTime.now().isAfter(seckillVoucher.getEnd_time())) {
//            throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR, "不在优惠期间");
//        }
//        //判断优惠卷库存
//        if (seckillVoucher.getStock() <= 0) throw new BusinessException(ResponseCodeEnum.NOT_FOUND_ERROR, "库存不足");
//
//        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
//        Long userId = user.getId();
//        //单机实现悲观锁互斥
////        synchronized (userId.toString().intern()) {
////            VoucherOrderService proxy= (VoucherOrderService) AopContext.currentProxy();
////            return proxy.createVoucherOrder(voucherId, seckillVoucher, userId);
////        }
//        //使用自定义的redis分布式锁
//        //Boolean success = distributedLock.tryLock(VOUCHER_ORDER_LOCK, voucherId, userId, 200, TimeUnit.SECONDS);
//
//
//
//
//        //使用redisson实现分布式锁
//        //获取锁对象
//        RLock lock = redissonClient.getLock(VOUCHER_ORDER_LOCK+voucherId+":"+userId);
//        boolean isLock = lock.tryLock();
//        if (isLock == false) throw new BusinessException(ResponseCodeEnum.OPERATION_ERROR,"请勿重复下单");
//        try {
//            VoucherOrderService proxy = (VoucherOrderService) AopContext.currentProxy();
//            long orderId = proxy.createVoucherOrder(voucherId, seckillVoucher, userId);
//            return orderId;
//        }catch (Exception e){
//            throw e;
//        }finally {
//            //distributedLock.unLock(VOUCHER_ORDER_LOCK,voucherId,userId);
//            lock.unlock();
//        }
//    }

    /**
     * 创建订单
     */
  //  @Override
   // @Transactional
//    public long createVoucherOrder(Long voucherId, SeckillVoucher seckillVoucher, Long userId) {
//        //查询订单是否存在
//        int num = this.query().eq("userId", userId).eq("voucherId", voucherId).count().intValue();
//        if (num > 0) {
//            throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR, "请勿重复购买秒杀卷");
//        }
//        //使用版本号法解决并发带来的数据安全问题
//        //库存减1
//        boolean success = seckillVoucherService.update()
//                .setSql("stock= stock -1") //set stock = stock -1
//                .eq("voucher_id", voucherId).eq("stock", seckillVoucher.getStock()).update(); //where id = ？ and stock = ?
//
//        if (success == true) {
//            //插入订单表
//            String statisticalUnit = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
//            long orderId = redisIdWorker.nextId(VOUCHER_ORDER + seckillVoucher.getVoucher_id(), statisticalUnit);
//            VoucherOrder voucherOrder = new VoucherOrder();
//            voucherOrder.setId(orderId);
//            voucherOrder.setUserId(userId);
//            voucherOrder.setVoucherId(voucherId);
//            this.save(voucherOrder);
//            return orderId;
//        }
//        throw new BusinessException(ResponseCodeEnum.OPERATION_ERROR, "并发下数据数据出现安全问题，请重试");
//    }
}




