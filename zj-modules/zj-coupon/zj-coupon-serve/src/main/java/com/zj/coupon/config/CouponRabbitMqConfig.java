package com.zj.coupon.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zj
 * @create 2022-09-03 9:26
 */
@Configuration
public class CouponRabbitMqConfig {

    @Bean
    public Queue createQueue(){
        return new Queue("coupon.forward", true);
    }

    @Bean
    public Exchange createExchange(){
        return new DirectExchange(
                "coupon.exchange",
                true,
                false
        );
    }

    @Bean
    public Binding createBinding(){
        return new Binding(
                "coupon.forward",
                Binding.DestinationType.QUEUE,
                "coupon.exchange",
                "coupon",
                null
        );
    }
}
