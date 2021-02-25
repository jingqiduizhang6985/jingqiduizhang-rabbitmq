package com.jingqiduizhang.rabbitmqservice.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 发送者
 */
@Component
public class RabbitMQSenderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送消息，不需要实现任何接口，供外部调用。
    public void send(String exchange,
                     String routingkey,
                     String message) {

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("开始发送消息 : " + message);
        rabbitTemplate.convertAndSend(exchange, routingkey, message, correlationData);
        System.out.println("结束发送消息 : " + message);
    }

    public void sendMessage(String message){
        this.send(RabbitFanoutExchangeConfig.FANOUT_EXCHANGE,"",message);
    }
}
