package com.zj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.common.security.utils.SecurityUtils;
import com.zj.coupon.remote.UserCouponServiceRemote;
import com.zj.system.common.domain.UserEntity;
import com.zj.system.mapper.UserMapper;
import com.zj.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zj
 * @create 2022-08-28 14:19
 */
@Service
 public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Autowired
    private UserCouponServiceRemote userCouponService;

    @Override
    public UserEntity userLogin(UserEntity userLogin) {
        return this.getBaseMapper().selectOne(
                new QueryWrapper<UserEntity>().lambda()
                        .eq(UserEntity::getUsername,userLogin.getUsername())
                        .eq(UserEntity::getPassword,userLogin.getPassword())
        );
    }

    @Override
    public void subscription() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(Integer.valueOf(SecurityUtils.getUserId().toString()));
        userEntity.setSubscription("1");
        this.updateById(userEntity);
        userCouponService.sendCoupon();
    }
}
