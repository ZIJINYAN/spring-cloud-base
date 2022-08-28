package com.zj.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.coupon.common.domain.CouponEntity;
import com.zj.coupon.mapper.CouponMapper;
import com.zj.coupon.service.ICouponService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 20:59
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, CouponEntity> implements ICouponService {
    @Override
    public List<CouponEntity> couponList() {
        return this.list(null);
    }
}
