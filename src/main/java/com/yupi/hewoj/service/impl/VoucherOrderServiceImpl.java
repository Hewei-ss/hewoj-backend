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
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static com.yupi.hewoj.constant.RedisContant.VOUCHER_ORDER;
import static com.yupi.hewoj.constant.RedisContant.VOUCHER_ORDER_LOCK;
import static com.yupi.hewoj.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 31695
 * @description 针对表【voucher_order(抢购优惠卷生成的订单)】的数据库操作Service实现
 * @createDate 2024-05-16 20:55:56
 */
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder>
        implements VoucherOrderService {

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private SeckillVoucherService seckillVoucherService;

    @Resource
    private DistributedLock distributedLock;

    /**
     * 秒杀优惠卷
     *
     * @param voucherId
     * @param httpServletRequest
     * @return
     */
    @Override
    public long seckillVoucher(Long voucherId, HttpServletRequest httpServletRequest) {
        //查询优惠卷信息
        SeckillVoucher seckillVoucher = seckillVoucherService.getById(voucherId);
        //判断是否在优惠时间范围内
        if (LocalDateTime.now().isBefore(seckillVoucher.getBegin_time())) {
            throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR, "不在优惠期间");
        }
        if (LocalDateTime.now().isAfter(seckillVoucher.getEnd_time())) {
            throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR, "不在优惠期间");
        }
        //判断优惠卷库存
        if (seckillVoucher.getStock() <= 0) throw new BusinessException(ResponseCodeEnum.NOT_FOUND_ERROR, "库存不足");

        User user = (User) httpServletRequest.getSession().getAttribute(USER_LOGIN_STATE);
        Long userId = user.getId();
//        synchronized (userId.toString().intern()) {
//            VoucherOrderService proxy= (VoucherOrderService) AopContext.currentProxy();
//            return proxy.createVoucherOrder(voucherId, seckillVoucher, userId);
//        }
        Boolean success = distributedLock.tryLock(VOUCHER_ORDER_LOCK, voucherId, userId, 200, TimeUnit.SECONDS);
        if (success == false) throw new RuntimeException();
        try {
            VoucherOrderService proxy = (VoucherOrderService) AopContext.currentProxy();
            long orderId = proxy.createVoucherOrder(voucherId, seckillVoucher, userId);
            return orderId;
        }catch (Exception e){
            throw e;
        }finally {
            distributedLock.unLock(VOUCHER_ORDER_LOCK,voucherId,userId);
        }
    }

    /**
     * 创建订单
     */
    @Override
    @Transactional
    public long createVoucherOrder(Long voucherId, SeckillVoucher seckillVoucher, Long userId) {
        //查询订单是否存在
        int num = this.query().eq("userId", userId).eq("voucherId", voucherId).count().intValue();
        if (num > 0) {
            throw new BusinessException(ResponseCodeEnum.NO_AUTH_ERROR, "请勿重复购买秒杀卷");
        }
        //使用版本号法解决并发带来的数据安全问题
        //库存减1
        boolean success = seckillVoucherService.update()
                .setSql("stock= stock -1") //set stock = stock -1
                .eq("voucher_id", voucherId).eq("stock", seckillVoucher.getStock()).update(); //where id = ？ and stock = ?

        if (success == true) {
            //插入订单表
            String statisticalUnit = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
            long orderId = redisIdWorker.nextId(VOUCHER_ORDER + seckillVoucher.getVoucher_id(), statisticalUnit);
            VoucherOrder voucherOrder = new VoucherOrder();
            voucherOrder.setId(orderId);
            voucherOrder.setUserId(userId);
            voucherOrder.setVoucherId(voucherId);
            this.save(voucherOrder);
            return orderId;
        }
        throw new BusinessException(ResponseCodeEnum.OPERATION_ERROR, "并发下数据数据出现安全问题，请重试");
    }
}




