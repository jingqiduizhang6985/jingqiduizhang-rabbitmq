package com.jingqiduizhang.rabbitmqbase.routing;

import com.jingqiduizhang.rabbitmqbase.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * rabbitMq
 * 路由模式 交换机类型 direct
 * 发送时 指定一个key 如 案例中的 save 和update
 *
 */
public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 消息内容
        String message = "添加商品A id=1002";
        channel.basicPublish(EXCHANGE_NAME, "save", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        String message2 = "更新商品A id=1002";
        channel.basicPublish(EXCHANGE_NAME, "update", null, message2.getBytes());
        System.out.println(" [x] Sent '" + message2 + "'");

        channel.close();
        connection.close();
    }
}
