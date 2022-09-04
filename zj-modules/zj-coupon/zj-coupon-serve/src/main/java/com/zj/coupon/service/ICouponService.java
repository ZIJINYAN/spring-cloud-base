package com.zj.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.coupon.common.domain.CouponEntity;
import com.zj.coupon.common.domain.UserCouponEntity;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 20:58
 */
public interface ICouponService extends IService<CouponEntity> {

    UserCouponEntity randomCoupon();

    void importCoupon(List<CouponEntity> couponEntityList);
}
