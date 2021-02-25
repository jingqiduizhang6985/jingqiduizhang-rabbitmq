package com.jingqiduizhang.rabbitmqservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列 交换机 对象绑定
 * @author duran
 */
@Configuration
public class RabbitFanoutExchangeConfig {
    /**
     * 初始化交换机
     */
    public static final String FANOUT_EXCHANGE = "fanout.exchange";
    @Bean(name = FANOUT_EXCHANGE)
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE, true, false);
    }

    /**
     * 队列初始化
     */
    public static final String FANOUT_QUEUE1 = "fanout.queue1";
    @Bean(name = FANOUT_QUEUE1)
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE1, true, false, false);
    }
    /**
     * 交换机队列绑定
     * @Qualifier 标识按照类名称去查找对象
     */
    @Bean
    public Binding bindingSimpleQueue1(@Qualifier(FANOUT_QUEUE1) Queue fanoutQueue1,
                                       @Qualifier(FANOUT_EXCHANGE) FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }
}
