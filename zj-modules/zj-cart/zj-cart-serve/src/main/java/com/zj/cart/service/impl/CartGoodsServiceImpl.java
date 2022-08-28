package com.zj.cart.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.cart.common.domain.CartGoodsEntity;
import com.zj.cart.mapper.CartGoodsMapper;
import com.zj.cart.service.ICartGoodsService;
import org.springframework.stereotype.Service;

/**
 * @author zj
 * @create 2022-08-28 21:11
 */
@Service
public class CartGoodsServiceImpl extends ServiceImpl<CartGoodsMapper, CartGoodsEntity> implements ICartGoodsService {
}
