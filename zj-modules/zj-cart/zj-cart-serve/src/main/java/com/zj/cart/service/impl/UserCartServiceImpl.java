package com.zj.cart.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.cart.common.domain.CartGoodsEntity;
import com.zj.cart.common.domain.UserCartEntity;
import com.zj.cart.common.domain.vo.CartGoodsItemVo;
import com.zj.cart.common.domain.vo.UserCartVo;
import com.zj.cart.mapper.UserCartMapper;
import com.zj.cart.service.ICartGoodsService;
import com.zj.cart.service.IUserCartService;
import com.zj.system.common.domain.vo.GoodsItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public UserCartVo cartInfo() {

        UserCartVo userCartVo = new UserCartVo();
        UserCartEntity userCartInfo = this.getBaseMapper().selectOne(
                new QueryWrapper<UserCartEntity>().lambda()
                        .eq(UserCartEntity::getUserId, 1) // 存储登录用户信息 transmittable-thread-local
        );
        List<CartGoodsEntity> cartGoodsItemList = cartGoodsService.getBaseMapper().selectList(
                new QueryWrapper<CartGoodsEntity>().lambda()
                        .eq(CartGoodsEntity::getCartId, userCartInfo.getCartId())
        );
        Map<Object, Object> goodsAll = redisTemplate.boundHashOps("goods_all").entries();
        List<CartGoodsItemVo> cartGoodsItemVoList = cartGoodsItemList.stream().map(cartGoodsItem -> {
            GoodsItemVo goodsItemVo = JSONObject.parseObject(goodsAll.get(cartGoodsItem.getGoodsId().toString()).toString(), GoodsItemVo.class);
            CartGoodsItemVo cartGoodsItemVo = new CartGoodsItemVo();
            cartGoodsItemVo.setCartGoodsEntity(cartGoodsItem);
            cartGoodsItemVo.setGoodsCode(goodsItemVo.getGoodsEntity().getGoodsCode());
            cartGoodsItemVo.setGoodsName(goodsItemVo.getGoodsEntity().getGoodsName());
            cartGoodsItemVo.setGoodsPrice(goodsItemVo.getGoodsEntity().getGoodsPrice());
            cartGoodsItemVo.setCartGoodsEntity(cartGoodsItem);
            return cartGoodsItemVo;
        }).collect(Collectors.toList());
        userCartVo.setUserCartEntity(userCartInfo);
        userCartVo.setCartGoodsItemVoList(cartGoodsItemVoList);
        return userCartVo;
    }
}
