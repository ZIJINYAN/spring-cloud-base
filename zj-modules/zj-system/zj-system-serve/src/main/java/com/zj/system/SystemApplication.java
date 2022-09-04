package com.zj.system;

import com.zj.common.security.annotation.EnableCustomConfig;
import com.zj.common.security.annotation.EnableZJFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zj
 * @create 2022-08-28 14:11
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCustomConfig
@EnableZJFeignClients
@EnableScheduling
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
