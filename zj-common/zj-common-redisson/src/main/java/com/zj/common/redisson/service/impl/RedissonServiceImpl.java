package com.zj.common.redisson.service.impl;

import com.zj.common.redisson.service.RedissonService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @deprecation redisson 分布式锁能力提供层
 * @author zj
 */
@Component
public class RedissonServiceImpl implements RedissonService {

    private final static Logger log = LoggerFactory.getLogger(RedissonServiceImpl.class);
    private final RedissonClient redissonClient;

    public RedissonServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
    /**
     * 加锁
     * @param key 锁的 key
     * @param value  value （ key + value 必须保证唯一）
     * @param expire key 的过期时间，单位 ms
     * @param retryTimes 重试次数，即加锁失败之后的重试次数
     * @param retryInterval 重试时间间隔，单位 ms
     * @return 加锁 true 成功
     */
    public RLock lock(String key, String value, long expire, int retryTimes, long retryInterval) {
        log.info("准备锁定分布式锁：{}", key);
        RLock fairLock = redissonClient.getFairLock(key + ":" +  value);
        try {
            boolean tryLock = fairLock.tryLock(0, expire, TimeUnit.MILLISECONDS);
            if (tryLock) {
                log.info("获取并锁定分布式锁：{}", key);
                return fairLock;
            } else {
                //重试获取锁
                log.info("重试获取锁： [分布式锁 = {}]", key);
                int count = 0;
                while(count < retryTimes) {
                    try {
                        Thread.sleep(retryInterval);
                        tryLock = fairLock.tryLock(0, expire, TimeUnit.MILLISECONDS);
                        if(tryLock) {
                            log.info("获取并锁定分布式锁：{}", key);
                            return fairLock;
                        }
                        count++;
                        log.warn("{} 次尝试获取锁", count);
                    } catch (Exception e) {
                        log.error("获取redis分布式锁发生异常", e);
                        break;
                    }
                }
                log.info("获取锁失败：{}", key);
            }
        } catch (Throwable e1) {
            log.error("获取redis发生异常", e1);
        }
        return fairLock;
    }
    /**
     * 加锁
     * @param key 锁的 key
     * @param value  value （ key + value 必须保证唯一）
     * @param expire key 的过期时间，单位 ms
     * @return 加锁 true 成功
     */
    public boolean lockCheck(String key, String value, long expire) {
        log.info("准备获取分布式锁：{}", key);
        RLock fairLock = redissonClient.getFairLock(key + ":" +  value);
        boolean tryLock = false;
        try {
            tryLock = fairLock.tryLock(0, expire, TimeUnit.MILLISECONDS);
        } catch (Throwable e1) {
            log.error("获取redis分布式锁发生异常", e1);
        }
        return tryLock;
    }
    /**
     * 释放KEY
     * @return 释放锁 true 成功
     */
    public boolean unlock(String key, String value) {
        RLock fairLock = redissonClient.getFairLock(key + ":" +  value);
        try {
            //如果这里抛异常，后续锁无法释放
            if (fairLock.isLocked()) {
                fairLock.unlock();
                log.info("释放锁成功");
                return true;
            }
        } catch (Throwable e) {
            log.error("释放锁发生异常", e);
        }
        return false;
    }
}