package com.zj.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.cart.common.domain.CartGoodsEntity;
import com.zj.cart.common.domain.UserCartEntity;
import com.zj.cart.common.domain.vo.UserCartVo;
import com.zj.cart.service.ICartGoodsService;
import com.zj.cart.service.IUserCartService;
import com.zj.cart.mapper.UserCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 21:04
 */
@Service
public class UserCartServiceImpl extends ServiceImpl<UserCartMapper, UserCartEntity> implements IUserCartService {

    @Autowired
    private ICartGoodsService cartGoodsService;

    @Override
    public UserCartVo cartInfo() {
        int userId = 1;
        UserCartVo userCartVo = new UserCartVo();
        UserCartEntity userCartIdf = this.getBaseMapper().selectOne(
                new QueryWrapper<UserCartEntity>().lambda()
                        .eq(UserCartEntity::getUserId, userId)
        );
        List<CartGoodsEntity> cartGoodsItemList = cartGoodsService.getBaseMapper().selectList(
                new QueryWrapper<CartGoodsEntity>().lambda()
                        .eq(CartGoodsEntity::getCartId, userCartIdf.getCartId())
        );
        userCartVo.setUserCartEntity(userCartIdf);
        userCartVo.setCartGoodsEntityList(cartGoodsItemList);
        return userCartVo;
    }
}
