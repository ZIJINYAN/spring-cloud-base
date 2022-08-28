package com.zj.coupon;

import com.zj.common.security.annotation.EnableCustomConfig;
import com.zj.common.security.annotation.EnableZJFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zj
 * @create 2022-08-28 20:41
 */
@SpringBootApplication
@EnableZJFeignClients
@EnableCustomConfig
public class CouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class, args);
    }
}
