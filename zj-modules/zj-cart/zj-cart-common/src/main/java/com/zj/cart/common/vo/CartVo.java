package com.zj.cart.common.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zj
 * @create 2022-09-04 9:14
 */
@Data
public class CartVo {
    List<CartItemVo> cartItemVoList;

    private BigDecimal totalPrice;

    private BigDecimal reducePrice;

    private String couponCode;
}
