package com.zj.coupon.listenner;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.zj.coupon.common.domain.UserCouponEntity;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zj
 * @create 2022-09-03 9:30
 */
@RabbitListener(queues = "coupon.forward")
@Component
public class CouponListener {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RabbitHandler
    public void couponForward(String couponJson, Message message, Channel channel){
        MessageProperties messageProperties = message.getMessageProperties();
        try {
            UserCouponEntity coupon = JSONObject.parseObject(couponJson, UserCouponEntity.class);
            if(!redisTemplate.hasKey(coupon.getCouponCode())){
                redisTemplate.opsForValue().set(coupon.getCouponCode(),couponJson);
                // todo 发送短信
            }
            channel.basicAck(messageProperties.getDeliveryTag(),false);
        } catch (IOException e) {
            try {
                channel.basicNack(messageProperties.getDeliveryTag(),false, true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
