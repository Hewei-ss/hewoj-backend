package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hewoj.model.entity.SeckillVoucher;
import com.yupi.hewoj.model.entity.VoucherOrder;

import javax.servlet.http.HttpServletRequest;

/**
* @author 31695
* @description 针对表【voucher_order(抢购优惠卷生成的订单)】的数据库操作Service
* @createDate 2024-05-16 20:55:56
*/
public interface VoucherOrderService extends IService<VoucherOrder> {
    
    long seckillVoucher(Long voucherId, HttpServletRequest httpServletRequest);

    void createVoucherOrder(VoucherOrder voucherOrder);

//    long createVoucherOrder(Long voucherId, SeckillVoucher seckillVoucher, Long userId);
}
