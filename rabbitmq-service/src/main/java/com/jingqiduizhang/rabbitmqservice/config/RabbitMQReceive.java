package com.jingqiduizhang.rabbitmqservice.config;

import com.jingqiduizhang.rabbitmqservice.servcice.StudentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 接收者
 */
@Component
public class RabbitMQReceive {
    @Autowired
    private StudentService studentService;

    @RabbitListener(queues = RabbitFanoutExchangeConfig.FANOUT_QUEUE1)
    public void receiveLogAll(String msg) throws Exception {
        studentService.receiveMessage(msg);
        System.out.println("log.all:" + msg);
    }
}
