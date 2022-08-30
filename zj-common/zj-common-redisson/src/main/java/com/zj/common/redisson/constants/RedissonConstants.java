package com.zj.common.redisson.constants;

import lombok.Data;

/**
 * @author zj
 * @create 2022-08-05 15:19
 */
@Data
public class RedissonConstants {
    /**
     * 过期时间
     */
    public static final long TIME_EXPIRE = 10000;
    /**
     * 尝试次数
     */
    public static final int RETRY_TIMES = 10;


    public static final long RETRY_INTERVAL = 1000;
}
