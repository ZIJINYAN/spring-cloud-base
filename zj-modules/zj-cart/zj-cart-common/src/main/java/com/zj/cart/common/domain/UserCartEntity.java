package com.zj.cart.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zj
 * @create 2022-08-28 20:35
 */
@Data
@TableName("cart_user_cart")
public class UserCartEntity {


    /**
     * 购物车ID
     */
    @TableId(type = IdType.AUTO)
    private Integer cartId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 优惠劵编号
     */
    private String couponCode;
    /**
     * 优惠劵ID
     */
    private Integer couponId;
    /**
     * 总计金额
     */
    private BigDecimal cartTotalPrice;
    /**
     * 优惠金额
     */
    private BigDecimal cartTotalPay;



}
