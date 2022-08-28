package com.zj.coupon.common.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zj
 * @create 2022-08-28 20:28
 */
@Data
@TableName("coupon_coupon")
public class CouponEntity {

    /**
     * 优惠劵ID
     */
    @TableId
    private Integer couponId;
    /**
     * 优惠劵名称
     */
    private String couponName;
    /**
     * 满足金额
     */
    private BigDecimal couponFull;
    /**
     * 优惠金额
     */
    private BigDecimal couponReduce;
    /**
     * 优惠劵数量
     */
    private Integer couponNum;

}
