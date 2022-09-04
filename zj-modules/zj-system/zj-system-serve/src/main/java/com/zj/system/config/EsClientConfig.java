package com.zj.system.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zj
 * @create 2022-09-02 13:58
 */
@Configuration
public class EsClientConfig {

    @Bean
    public RestHighLevelClient getRestHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.60.128",9200,"http"))
        );
    }

}
