package com.yupi.hewoj.model.dto.voucher;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 优惠卷和秒杀卷添加请求
 */
@Data
public class VoucherAddRequest implements Serializable {

    /**
     * 代金券标题
     */
    private String title;

    /**
     * 副标题
     */
    private String sub_title;

    /**
     * 使用规则
     */
    private String rules;

    /**
     * 支付金额，单位是分。例如200代表2元
     */
    private Long pay_value;

    /**
     * 抵扣金额，单位是分。例如200代表2元
     */
    private Long actual_value;

    /**
     * 0,普通券；1,秒杀券
     */
    private Integer type;

    /**
     * 1,上架; 2,下架; 3,过期
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 更新时间
     */
    private LocalDateTime update_time;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 库存
     */
    private Integer stock;
    /**
     * 生效时间
     */
    private LocalDateTime beginTime;

    /**
     * 失效时间
     */
    private LocalDateTime endTime;

    private static final long serialVersionUID = 1L;
}
