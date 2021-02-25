package com.jingqiduizhang.rabbitmqbase.topic;

import com.jingqiduizhang.rabbitmqbase.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * rabbitMq 生产者
 * 交换机模式 topic
 * 生产者 发送端 不需要绑定队列 只需要绑定交换机即可。
 * 把数据发送到交换机上
 */
public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        // 消息内容
        String message = "保存商品 id=1002";
        channel.basicPublish(EXCHANGE_NAME, "item.save", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        String message1 = "更新商品 id=1002";
        channel.basicPublish(EXCHANGE_NAME, "item.update", null, message1.getBytes());
        System.out.println(" [x] Sent '" + message1 + "'");

        channel.close();
        connection.close();
    }
}
