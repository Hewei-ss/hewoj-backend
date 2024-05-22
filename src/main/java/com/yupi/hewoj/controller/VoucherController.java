package com.yupi.hewoj.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.hewoj.common.BaseResponse;
import com.yupi.hewoj.common.PageRequest;
import com.yupi.hewoj.common.ResultUtils;
import com.yupi.hewoj.exception.ThrowUtils;
import com.yupi.hewoj.model.dto.question.QuestionQueryRequest;
import com.yupi.hewoj.model.dto.voucher.VoucherAddRequest;
import com.yupi.hewoj.model.entity.Question;
import com.yupi.hewoj.model.entity.SeckillVoucher;
import com.yupi.hewoj.model.entity.Voucher;
import com.yupi.hewoj.model.enums.ResponseCodeEnum;
import com.yupi.hewoj.model.vo.QuestionVO;
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

//    @PostMapping("/list/page/")
//    public BaseResponse<Page<Voucher>> listQuestionVOByPage(@RequestBody PageRequest pageRequest, HttpServletRequest request) {
//
//        //当前要返回的页码
//        long current = pageRequest.getCurrent();
//        //页面大小
//        long size = pageRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ResponseCodeEnum.PARAMS_ERROR);
//        Page<Voucher> voucherPage = voucherService.page(new Page<>(current, size),
//                voucherService.getQueryWrapper(questionQueryRequest));
//        return ResultUtils.success(questionService.getQuestionVOPage(questionPage, request));
//    }

}