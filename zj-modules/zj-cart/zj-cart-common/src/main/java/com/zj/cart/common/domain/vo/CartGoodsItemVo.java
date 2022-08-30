package com.zj.cart.common.domain.vo;

import com.zj.cart.common.domain.CartGoodsEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zj
 * @create 2022-08-30 9:32
 */
@Data
public class CartGoodsItemVo {
    /**
     * 购物车商品详细信息
     */
    private CartGoodsEntity cartGoodsEntity;
    /**
     * 商品编号
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

}
