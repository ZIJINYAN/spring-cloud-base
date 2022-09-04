package com.zj.coupon.controller;

import com.zj.common.core.domain.Result;
import com.zj.coupon.common.domain.UserCouponEntity;
import com.zj.coupon.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @create 2022-08-28 20:57
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private ICouponService couponService;

    @PostMapping("/randomCoupon")
    public Result<UserCouponEntity> randomCoupon(){
        return Result.success(couponService.randomCoupon(), "成功");
    }
}
