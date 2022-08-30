package com.zj.cart.common.domain.vo;

import com.zj.cart.common.domain.UserCartEntity;
import lombok.Data;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 23:26
 */
@Data
public class UserCartVo {
    /**
     * 购物车商品信息
     */
    private List<CartGoodsItemVo> cartGoodsItemVoList;
    /**
     * 购物车信息
     */
    private UserCartEntity userCartEntity;

}
