package com.yupi.hewoj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.hewoj.model.entity.SeckillVoucher;

import java.util.List;

/**
* @author 31695
* @description 针对表【seckill_voucher(秒杀优惠券表，与优惠券是一对一关系)】的数据库操作Mapper
* @createDate 2024-05-16 17:10:46
* @Entity generator.domain.SeckillVoucher
*/
public interface SeckillVoucherMapper extends BaseMapper<SeckillVoucher> {
    List<SeckillVoucher> listSecKillVoucher();

}




