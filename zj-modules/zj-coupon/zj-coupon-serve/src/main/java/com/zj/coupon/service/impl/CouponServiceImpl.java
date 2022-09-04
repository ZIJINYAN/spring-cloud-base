package com.zj.coupon.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.common.redisson.service.RedissonService;
import com.zj.common.security.utils.SecurityUtils;
import com.zj.coupon.common.domain.CouponEntity;
import com.zj.coupon.common.domain.UserCouponEntity;
import com.zj.coupon.mapper.CouponMapper;
import com.zj.coupon.service.ICouponService;
import com.zj.coupon.service.IUserCouponService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zj
 * @create 2022-08-28 20:59
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, CouponEntity> implements ICouponService {


    @Autowired
    private IUserCouponService userCouponService;

    @Autowired
    private RedissonService redissonService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    @Override
    public UserCouponEntity randomCoupon() {
        CouponEntity couponConfirm = null;
        synchronized (this) {
            //查询出已知通用的优惠劵
            List<CouponEntity> couponList = this.list(null);
            //随机抽取一种类型的优惠劵 TODO 概率分布
            CouponEntity couponEntity = RandomUtil.randomEle(couponList);
            redissonService.defaultLock("random_coupon", couponEntity.getCouponId().toString());
            //再次查询随机到的优惠劵类型的数量
            couponConfirm = this.getById(couponEntity.getCouponId());
            // 扣除对应优惠劵的数量
            if (couponConfirm.getCouponNum()!=null && couponConfirm.getCouponNum() > 0) {
                CouponEntity coupon = new CouponEntity();
                coupon.setCouponId(couponEntity.getCouponId());
                this.getBaseMapper().update(coupon, new UpdateWrapper<CouponEntity>().lambda().setSql("coupon_num = coupon_num - 1")
                        .eq(CouponEntity::getCouponId, coupon.getCouponId()));
            } else {
                couponConfirm = new CouponEntity().getDefaultCoupon();
            }
            // 构建用户所持有优惠劵对象
            UserCouponEntity userCouponEntity = new UserCouponEntity();
            userCouponEntity.setUserId(Integer.valueOf(SecurityUtils.getUserId().toString()));
            userCouponEntity.setCouponId(couponConfirm.getCouponId());
            // 雪花算法生成优惠劵唯一ID
            String couponCode = IdUtil.createSnowflake(1, 1).nextIdStr();
            userCouponEntity.setCouponCode(couponCode);
            userCouponEntity.setCouponName(couponConfirm.getCouponName());
            userCouponEntity.setCouponFull(couponConfirm.getCouponFull());
            userCouponEntity.setCouponReduce(couponConfirm.getCouponReduce());
            // 保存到数据库
            userCouponService.save(userCouponEntity);
            rabbitTemplate.convertAndSend("coupon.forward","coupon", JSON.toJSONString(userCouponEntity));
            redissonService.unlock("random_coupon",couponEntity.getCouponId().toString());
            return userCouponEntity;
        }
    }


}
