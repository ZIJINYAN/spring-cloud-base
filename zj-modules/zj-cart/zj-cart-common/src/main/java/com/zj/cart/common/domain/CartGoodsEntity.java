package com.zj.cart.common.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zj
 * @create 2022-08-28 20:33
 */
@Data
@TableName("cart_goods")
public class CartGoodsEntity{

    /**
     * 购物车ID
     */
    @TableId
    private Integer cartId;
    /**
     * 商品ID
     */
    private Integer goodsId;
    /**
     * 商品数量
     */
    private Integer goodsNum;
    /**
     * 商品编号
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品类型
     */
    private Integer goodsType;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品描述
     */
    private String goodsDesc;
}
