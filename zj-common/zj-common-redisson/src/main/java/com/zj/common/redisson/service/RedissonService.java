package com.zj.common.redisson.service;

import com.zj.common.redisson.constants.RedissonConstants;
import org.redisson.api.RLock;

/**
 * @author zj
 * @description: redisson 能力提供层
 */
public interface RedissonService {
    /**
     * 加锁
     * @param key 锁的 key
     * @param value  value （ key + value 必须保证唯一）
     * @param expire key 的过期时间，单位 ms
     * @param retryTimes 重试次数，即加锁失败之后的重试次数
     * @param retryInterval 重试时间间隔，单位 ms
     * @return 加锁 true 成功
     */
    public RLock lock(String key, String value, long expire, int retryTimes, long retryInterval);
    /**
     * 加锁
     * @param key 锁的 key
     * @param value  value （ key + value 必须保证唯一）
     * @param expire key 的过期时间，单位 ms
     * @return 加锁 true 成功
     */
    public boolean lockCheck(String key, String value, long expire);
    /**
     * 释放KEY
     * @return 释放锁 true 成功
     */
    public boolean unlock(String key, String value);

    /**
     *
     * @param key 锁的 key
     * @param value value （ key + value 必须保证唯一）
     * @return 加锁 true 成功
     */
    default public RLock defaultLock(String key, String value) {
        return this.lock(
                key,
                value,
                RedissonConstants.TIME_EXPIRE,
                RedissonConstants.RETRY_TIMES,
                RedissonConstants.RETRY_INTERVAL
        );
    }
}