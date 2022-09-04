package com.zj.coupon.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.common.security.utils.SecurityUtils;
import com.zj.coupon.common.domain.UserCouponEntity;
import com.zj.coupon.mapper.UserCouponMapper;
import com.zj.coupon.service.IUserCouponService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 21:15
 */
@Service
 public class UserCouponServiceImpl extends ServiceImpl<UserCouponMapper, UserCouponEntity> implements IUserCouponService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public UserCouponEntity sendCoupon() {
        BigDecimal reduce = NumberUtil.round(
                RandomUtil.randomBigDecimal(new BigDecimal("0"),new BigDecimal("100")),
                2);
        BigDecimal full = getFullPrice(reduce);
        UserCouponEntity userCouponEntity = new UserCouponEntity();
        userCouponEntity.setUserId(Integer.valueOf(SecurityUtils.getUserId().toString()));
        userCouponEntity.setCouponCode(IdUtil.getSnowflake(1,1).nextIdStr());
        userCouponEntity.setCouponName(getCouponName(reduce,full));
        userCouponEntity.setCouponFull(full);
        userCouponEntity.setCouponReduce(reduce);
        this.save(userCouponEntity);
        rabbitTemplate.convertAndSend("coupon.forward","coupon", JSON.toJSONString(userCouponEntity));
        return userCouponEntity;
    }

    @Override
    public List<UserCouponEntity> myCouponList() {
        return this.list(
                new QueryWrapper<UserCouponEntity>().lambda().eq(UserCouponEntity::getUserId,SecurityUtils.getUserId())
        );
    }

    // 随机生成金额向上取整  70 --> 64.44
    public BigDecimal getFullPrice(BigDecimal reduce){
        return reduce
                .divide(new BigDecimal("10"))
                .setScale(0,BigDecimal.ROUND_UP)
                .multiply(new BigDecimal("10")
        );
    }
    // couponName 生成
    public String getCouponName(BigDecimal reduce, BigDecimal full){
        String couponNameTemplate = "优惠券满{}减{}";
        return StrUtil.format(couponNameTemplate,full,reduce);
    }
}
