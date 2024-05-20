package com.yupi.hewoj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.hewoj.model.dto.voucher.VoucherAddRequest;
import com.yupi.hewoj.model.entity.Voucher;

/**
* @author 31695
* @description 针对表【voucher(vip优惠卷表)】的数据库操作Service
* @createDate 2024-05-16 16:58:00
*/
public interface VoucherService extends IService<Voucher> {
    /**
     *
     * @param voucher
     * @return
     */
    boolean addSeckillVoucher(Voucher voucher);

}
