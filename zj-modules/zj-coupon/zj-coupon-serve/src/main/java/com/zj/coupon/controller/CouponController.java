package com.zj.coupon.controller;

import com.zj.common.core.domain.Result;
import com.zj.coupon.common.domain.CouponEntity;
import com.zj.coupon.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 20:57
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private ICouponService couponService;


    /**
     * 优惠劵列表
     * @return List<CouponEntity>
     */
    @GetMapping("/list")
    public Result<List<CouponEntity>> couponList(){
        return Result.success(couponService.couponList());
    }
}
