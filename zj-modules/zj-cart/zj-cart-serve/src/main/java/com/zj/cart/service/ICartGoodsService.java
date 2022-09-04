package com.zj.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.cart.common.domain.CartGoodsEntity;
import com.zj.cart.common.vo.CartItemVo;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 21:10
 */
public interface ICartGoodsService extends IService<CartGoodsEntity> {
    List<CartItemVo> cartList();
}
