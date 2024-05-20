package com.yupi.hewoj.controller;


import com.yupi.hewoj.common.BaseResponse;
import com.yupi.hewoj.common.ResultUtils;
import com.yupi.hewoj.model.dto.voucher.VoucherAddRequest;
import com.yupi.hewoj.model.entity.SeckillVoucher;
import com.yupi.hewoj.model.entity.Voucher;
import com.yupi.hewoj.service.SeckillVoucherService;
import com.yupi.hewoj.service.VoucherOrderService;
import com.yupi.hewoj.service.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/voucher")
@Slf4j
public class VoucherController {
    @Resource
    private VoucherService voucherService;

    @Resource
    private VoucherOrderService voucherOrderService;


    /**
     * 添加秒杀卷
     * @param voucherAddQuest
     * @return
     */
    @PostMapping("/addseckill")
    public BaseResponse<Boolean> addSeckillVoucher(@RequestBody VoucherAddRequest voucherAddQuest){
        Voucher voucher= new Voucher();
        BeanUtils.copyProperties(voucherAddQuest, voucher);
        boolean ch=voucherService.addSeckillVoucher(voucher);
        return ResultUtils.success(ch);
    }


    /**
     * 实现秒杀下单
     * @return
     */
    @GetMapping("/seckillvoucher")
    public BaseResponse<Long> seckillVoucher(@RequestParam("voucherId") Long voucherId, HttpServletRequest httpServletRequest){
        long orderId=voucherOrderService.seckillVoucher(voucherId,httpServletRequest);
        return ResultUtils.success(orderId);
    }
}
