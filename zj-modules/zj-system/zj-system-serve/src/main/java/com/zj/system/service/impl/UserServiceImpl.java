package com.zj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.common.core.domain.Result;
import com.zj.system.common.domain.UserEntity;
import com.zj.system.mapper.UserMapper;
import com.zj.system.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author zj
 * @create 2022-08-28 14:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {
    @Override
    public Result<UserEntity> login(UserEntity userLogin) {
        UserEntity userEntity = this.baseMapper.selectOne(
                new QueryWrapper<UserEntity>().lambda()
                        .eq(UserEntity::getUsername, userLogin.getUsername())
                        .eq(UserEntity::getPassword, userLogin.getPassword())
        );
        return Result.success(userEntity);
    }
}
