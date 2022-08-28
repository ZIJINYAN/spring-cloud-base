package com.zj.coupon.controller;

import com.zj.coupon.service.IUserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @create 2022-08-28 21:14
 */
@RestController
@RequestMapping("/user/coupon")
public class UserCouponController {
    @Autowired
    private IUserCouponService userCouponService;
}
