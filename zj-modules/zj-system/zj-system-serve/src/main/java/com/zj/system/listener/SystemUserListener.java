package com.zj.system.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author zj
 * @create 2022-08-29 20:50
 */
@RabbitListener(queues = "system.user.queue.sms")
@Component
public class SystemUserListener {

    @RabbitHandler
    public void loginInform(String msg, Message message, Channel channel){
        MessageProperties properties = message.getMessageProperties();
        try {
            System.out.println(msg + "已通知 !");
            channel.basicAck(properties.getDeliveryTag(),false);
        } catch (IOException e) {
            try {
                channel.basicNack(properties.getDeliveryTag(),false,true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
