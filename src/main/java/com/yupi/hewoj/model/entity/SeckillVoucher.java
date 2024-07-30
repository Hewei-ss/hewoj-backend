package com.yupi.hewoj.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 秒杀优惠券表，与优惠券是一对一关系
 * @TableName seckill_voucher
 */
@TableName(value ="seckill_voucher")
@Data
public class SeckillVoucher implements Serializable {
    /**
     * 关联的优惠券的id
     */
    @TableId
    private Long voucher_id;

    @TableField(exist = false)
    private Voucher voucher;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 创建时间
     */
    private LocalDateTime create_time;

    /**
     * 生效时间
     */
    private LocalDateTime begin_time;

    /**
     * 失效时间
     */
    private LocalDateTime end_time;

    /**
     * 更新时间
     */
    private LocalDateTime update_time;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}