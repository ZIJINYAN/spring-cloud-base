package com.zj.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.cart.common.domain.UserCartEntity;
import com.zj.cart.common.domain.vo.UserCartVo;

/**
 * @author zj
 * @create 2022-08-28 21:02
 */
public interface IUserCartService extends IService<UserCartEntity> {

    UserCartVo cartInfo();
}
