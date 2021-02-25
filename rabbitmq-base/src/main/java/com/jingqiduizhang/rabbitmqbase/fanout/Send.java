package com.jingqiduizhang.rabbitmqbase.fanout;

import com.jingqiduizhang.rabbitmqbase.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * rabbitMq 生产者
 * 订阅模式 交换机类型 fanout
 * 生产者 发送端 不需要绑定队列 只需要绑定交换机即可。
 * 把数据发送到交换机上
 */
public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
