package com.zj.cart.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.cart.common.domain.UserCartEntity;
import com.zj.cart.service.IUserCartService;
import com.zj.cart.mapper.UserCartMapper;
import org.springframework.stereotype.Service;

/**
 * @author zj
 * @create 2022-08-28 21:04
 */
@Service
public class UserCartServiceImpl extends ServiceImpl<UserCartMapper, UserCartEntity> implements IUserCartService {

}
