package com.zj.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.coupon.common.domain.CouponEntity;
import com.zj.coupon.common.domain.UserCouponEntity;

/**
 * @author zj
 * @create 2022-08-28 20:58
 */
public interface ICouponService extends IService<CouponEntity> {

    UserCouponEntity randomCoupon();
}
