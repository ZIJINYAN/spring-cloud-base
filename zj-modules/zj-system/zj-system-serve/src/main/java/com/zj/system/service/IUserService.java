package com.zj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.common.core.domain.Result;
import com.zj.system.common.domain.UserEntity;

/**
 * @author zj
 * @create 2022-08-28 14:18
 */
public interface IUserService extends IService<UserEntity> {
    Result<UserEntity> login(UserEntity userLogin);
}
