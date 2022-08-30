package com.zj.common.redisson.instance;

import com.alibaba.fastjson2.JSONObject;
import com.zj.common.core.utils.StringUtils;
import com.zj.common.redisson.config.RedissonConfig;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @deprecation redisson 实例
 * @author zj
 */
@Configuration
public class RedissonInstance {

    private final static Logger log = LoggerFactory.getLogger(RedissonInstance.class);
    /**
     * redis 配置类
     */
    private final RedissonConfig redissonConfig;
    public RedissonInstance(RedissonConfig redissonConfig) {
        this.redissonConfig = redissonConfig;
    }
    /**
     * 初始化RedissonClient客户端
     * 注意：
     * 此实例集群为3节点，各节点1主1从
     * 集群模式,集群节点的地址须使用“redis://”前缀，否则将会报错。
     *
     * @return {@link RedissonClient}
     */
    @Bean
    public RedissonClient getRedissonClient() {
        Config config = new Config();
        List<String> nodes = redissonConfig.getNodes();
        int nodeSize = nodes.size();
        if (nodes == null || nodeSize == 0){
            throw new RuntimeException("分布式锁 redisson 数据节点不可以为空");
        }
        if (nodeSize == 1){
            SingleServerConfig serverConfig = config.useSingleServer();
            // 获取节点
            serverConfig.setAddress(nodes.get(0));
            // 选择数据库
            serverConfig.setDatabase(redissonConfig.getDatabase());
            log.info("加载分布式锁：{}", nodes.get(0));
            // 设置密码
            if (StringUtils.isNotEmpty( redissonConfig.getPassword() )){
                log.info("redisson密码：{}", redissonConfig.getPassword());
                serverConfig.setPassword( redissonConfig.getPassword() );
            }
        }else {
            // 分布式锁
            ClusterServersConfig clusterServersConfig = config.useClusterServers().addNodeAddress(nodes.toArray(new String[nodeSize]));
            log.info("加载集群分布式锁：{}", JSONObject.toJSONString(nodes));
            // 加载密码
            if (StringUtils.isNotEmpty( redissonConfig.getPassword() )){
                log.info("redisson密码：{}", redissonConfig.getPassword());
                clusterServersConfig.setPassword( redissonConfig.getPassword() );
            }
        }
        //看门狗的锁续期时间，默认30000ms，这里配置成15000ms
        config.setLockWatchdogTimeout(10000);
        return Redisson.create(config);
    }
}