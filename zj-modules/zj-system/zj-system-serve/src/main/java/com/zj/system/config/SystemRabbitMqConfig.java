package com.zj.system.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zj
 * @create 2022-08-29 20:34
 */
@Configuration
public class SystemRabbitMqConfig {
    /**
     * name,
     * durable,
     * exclusive,
     * autoDelete,
     * arguments
     *
     * @return
     */
    @Bean
    public Queue initQueue() {
        return new Queue(
                "system.user.queue.sms",
                true,
                false,
                false,
                null
        );
    }

    /**
     * name
     * durable
     * autoDelete
     * arguments
     *
     * @return
     */
    @Bean
    public Exchange createExchange(){
        return new DirectExchange(
                "system.user.direct.exchange",
                true,
                false,
                null
        );
    }

    /**
     * destination
     * destinationType
     * exchange
     * routingKey
     * arguments
     *
     * @return
     */
    @Bean
    public Binding createBinding(){
        return new Binding(
                "system.user.queue.sms",
                Binding.DestinationType.QUEUE,
                "system.user.direct.exchange",
                "system.user",
                null
        );
    }
}
