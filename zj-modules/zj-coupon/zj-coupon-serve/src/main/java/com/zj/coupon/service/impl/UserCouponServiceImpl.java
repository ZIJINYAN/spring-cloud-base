package com.zj.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.coupon.common.domain.UserCouponEntity;
import com.zj.coupon.mapper.UserCouponMapper;
import com.zj.coupon.service.IUserCouponService;
import org.springframework.stereotype.Service;

/**
 * @author zj
 * @create 2022-08-28 21:15
 */
@Service
public class UserCouponServiceImpl extends ServiceImpl<UserCouponMapper, UserCouponEntity> implements IUserCouponService {
}
