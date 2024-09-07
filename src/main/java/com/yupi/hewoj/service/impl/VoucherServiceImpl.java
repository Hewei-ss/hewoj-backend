package com.yupi.hewoj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.hewoj.mapper.SeckillVoucherMapper;
import com.yupi.hewoj.mapper.VoucherMapper;
import com.yupi.hewoj.model.dto.voucher.VoucherAddRequest;
import com.yupi.hewoj.model.entity.SeckillVoucher;
import com.yupi.hewoj.model.entity.Voucher;
import com.yupi.hewoj.service.SeckillVoucherService;
import com.yupi.hewoj.service.VoucherService;
import freemarker.cache.StringTemplateLoader;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.yupi.hewoj.constant.RedisContant.SECKILL_STOCK_KEY;

/**
* @author 31695
* @description 针对表【voucher(vip优惠卷表)】的数据库操作Service实现
* @createDate 2024-05-16 16:58:00
*/
@Service
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher>
    implements VoucherService {

    @Resource
    private SeckillVoucherService seckillVoucherService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional
    public boolean addSeckillVoucher(Voucher voucher) {
        boolean ch1=save(voucher);
        SeckillVoucher seckillVoucher=new SeckillVoucher();
        seckillVoucher.setVoucher_id(voucher.getId());
        seckillVoucher.setStock(voucher.getStock());
        seckillVoucher.setBegin_time(voucher.getBeginTime());
        seckillVoucher.setEnd_time(voucher.getEndTime());
        boolean ch2=seckillVoucherService.save(seckillVoucher);
        stringRedisTemplate.opsForValue().set(SECKILL_STOCK_KEY+voucher.getId(),voucher.getStock().toString());
        return ch2&&ch1;
    }
}




