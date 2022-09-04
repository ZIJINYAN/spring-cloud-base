package com.zj.cart.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    @TableId(type = IdType.AUTO)
    private Integer cartId;
    /**
     * 商品ID
     */
    private Integer goodsId;
    /**
     * 商品数量
     */
    private Integer goodsNum;

}
