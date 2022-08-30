package com.zj.common.redisson.config;


import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zj
 * @description: Redisson 配置类
 */
@Configuration
@ConfigurationProperties(prefix = "redisson.config")
public class RedissonConfig {

    private final static Logger log = LoggerFactory.getLogger(RedissonConfig.class);
    /**
     * 节点
     */
    private List<String> nodes;
    /**
     * 密码
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    /**
     * 数据库
     */
    private int database = 0;
    public void setPassword(String password) {
        log.info("加载redisson集群配置密码：{}", password);
        this.password = password;
    }
    public List<String> getNodes() {
        if (nodes == null){
            throw new RuntimeException("redisson节点不可以为null，请设置配置文件：[redisson.config.nodes]");
        }
        return this.nodes.stream().map(node -> node = "redis://" + node).collect(Collectors.toList());
    }
    public void setNodes(List<String> nodes) {
        log.info("加载redisson集群配置节点：{}", JSONObject.toJSONString(nodes));
        this.nodes = nodes;
    }
}