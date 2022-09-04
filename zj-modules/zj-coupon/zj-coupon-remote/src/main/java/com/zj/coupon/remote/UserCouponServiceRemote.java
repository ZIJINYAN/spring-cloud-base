package com.zj.coupon.remote;

import com.zj.common.core.domain.Result;
import com.zj.coupon.common.domain.UserCouponEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author zj
 * @create 2022-09-04 9:50
 */
@FeignClient(contextId = "userCouponServiceRemote", value = "ZJ-coupon")
public interface UserCouponServiceRemote {

    @PostMapping("/user/coupon/sendCoupon")
    public Result<UserCouponEntity> sendCoupon();
}
