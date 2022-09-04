package com.zj.coupon.controller;

import com.zj.common.core.domain.Result;
import com.zj.coupon.common.domain.UserCouponEntity;
import com.zj.coupon.service.IUserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 21:14
 */
@RestController
@RequestMapping("/user/coupon")
public class UserCouponController {
    @Autowired
    private IUserCouponService userCouponService;

    @PostMapping("/sendCoupon")
    public Result<UserCouponEntity> sendCoupon(){
        return Result.success(userCouponService.sendCoupon());
    }

    @GetMapping("/myCouponList")
    public Result<List<UserCouponEntity>> myCouponList(){
        return Result.success(userCouponService.myCouponList());
    }
}
