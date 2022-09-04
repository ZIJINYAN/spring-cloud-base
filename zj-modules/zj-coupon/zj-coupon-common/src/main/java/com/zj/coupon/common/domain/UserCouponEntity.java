package com.zj.coupon.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zj
 * @create 2022-08-28 20:31
 */
@Data
@TableName("coupon_user_coupon")
public class UserCouponEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 优惠劵ID
     */
    private Integer couponId;
    /**
     * 优惠劵编号
     */
    private String couponCode;
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

}
