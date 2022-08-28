package com.zj.cart;

import com.zj.common.security.annotation.EnableCustomConfig;
import com.zj.common.security.annotation.EnableZJFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zj
 * @create 2022-08-28 20:42
 */
@SpringBootApplication
@EnableZJFeignClients
@EnableCustomConfig
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}
