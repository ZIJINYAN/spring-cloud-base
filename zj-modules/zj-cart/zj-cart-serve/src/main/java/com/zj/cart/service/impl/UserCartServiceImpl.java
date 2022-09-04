package com.zj.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.cart.common.domain.UserCartEntity;
import com.zj.cart.common.vo.CartItemVo;
import com.zj.cart.mapper.UserCartMapper;
import com.zj.cart.service.ICartGoodsService;
import com.zj.cart.service.IUserCartService;
import com.zj.common.security.utils.SecurityUtils;
import com.zj.system.common.vo.GoodsItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zj
 * @create 2022-08-28 21:04
 */
@Service
public class UserCartServiceImpl extends ServiceImpl<UserCartMapper, UserCartEntity> implements IUserCartService {

    @Autowired
    private ICartGoodsService cartGoodsService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void addCart(GoodsItemVo goodsItemVo) {
        BoundHashOperations<String, Object, Object> boundHashOps = redisTemplate.boundHashOps("user_cart_" + SecurityUtils.getUserId());
        String goodsId = goodsItemVo.getGoodsId().toString();
        if(boundHashOps.hasKey(goodsId)){
            CartItemVo cartItemVo = JSONObject.parseObject(boundHashOps.get(goodsId).toString(), CartItemVo.class);
            cartItemVo.setGoodsNum(cartItemVo.getGoodsNum() + 1);
            boundHashOps.put(goodsId, JSON.toJSONString(cartItemVo));
        } else {
            CartItemVo cartItemVo = new CartItemVo();
            cartItemVo.setGoodsTypeId(goodsItemVo.getGoodsTypeId());
            cartItemVo.setGoodsTypeName(goodsItemVo.getGoodsTypeName());
            cartItemVo.setGoodsId(goodsItemVo.getGoodsId());
            cartItemVo.setGoodsCode(goodsItemVo.getGoodsCode());
            cartItemVo.setGoodsName(goodsItemVo.getGoodsName());
            cartItemVo.setGoodsPrice(goodsItemVo.getGoodsPrice());
            cartItemVo.setGoodsDesc(goodsItemVo.getGoodsDesc());
            cartItemVo.setGoodsSales(goodsItemVo.getGoodsSales());
            cartItemVo.setGoodsStatus(goodsItemVo.getGoodsStatus());
            cartItemVo.setGoodsNum(1);
            boundHashOps.put(goodsId, JSON.toJSONString(cartItemVo));
        }
    }

    @Override
    public void removeCartGoodsItem(String goodsId) {
        BoundHashOperations<String, Object, Object> boundHashOps = redisTemplate.boundHashOps("user_cart_" + SecurityUtils.getUserId());
        if(boundHashOps.hasKey(goodsId)){
            boundHashOps.delete(goodsId);
        }
    }
}
