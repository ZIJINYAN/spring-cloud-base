package com.zj.cart.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.cart.common.domain.CartGoodsEntity;
import com.zj.cart.common.vo.CartItemVo;
import com.zj.cart.mapper.CartGoodsMapper;
import com.zj.cart.service.ICartGoodsService;
import com.zj.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zj
 * @create 2022-08-28 21:11
 */
@Service
public class CartGoodsServiceImpl extends ServiceImpl<CartGoodsMapper, CartGoodsEntity> implements ICartGoodsService {


    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public List<CartItemVo> cartList() {
        Long userId = SecurityUtils.getUserId();
        return redisTemplate.boundHashOps("user_cart_"+ userId).values().stream().map(
                item-> JSONObject.parseObject(item.toString(), CartItemVo.class)
        ).collect(Collectors.toList());
    }
}
