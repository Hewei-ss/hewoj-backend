package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.hewoj.exception.BusinessException;
import com.yupi.hewoj.mapper.SeckillVoucherMapper;
import com.yupi.hewoj.model.entity.SeckillVoucher;
import com.yupi.hewoj.model.entity.User;
import com.yupi.hewoj.model.entity.VoucherOrder;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.service.SeckillVoucherService;
import com.yupi.hewoj.service.VoucherOrderService;
import com.yupi.hewoj.utils.RedisIdWorker;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.AopProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.yupi.hewoj.constant.RedisContant.VOUCHER_ORDER;
import static com.yupi.hewoj.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 31695
 * @description 针对表【seckill_voucher(秒杀优惠券表，与优惠券是一对一关系)】的数据库操作Service实现
 * @createDate 2024-05-16 17:10:46
 */
@Service
public class SeckillVoucherServiceImpl extends ServiceImpl<SeckillVoucherMapper, SeckillVoucher>
        implements SeckillVoucherService {

    @Resource
    private SeckillVoucherMapper seckillVoucherMapper;
    @Override
    public List<SeckillVoucher> listSecKillVoucher() {
        return seckillVoucherMapper.listSecKillVoucher();
    }
}




