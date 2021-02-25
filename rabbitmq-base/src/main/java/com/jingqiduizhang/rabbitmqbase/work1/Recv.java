package com.jingqiduizhang.rabbitmqbase.work1;


import com.jingqiduizhang.rabbitmqbase.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
/**
 * 消费者一
 * rabbitMq work 模式  一对多发送  1个发送 多个接收 相互抢数据
 * 接收数据轮询平均分配
 */
public class Recv {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
            //休眠
            Thread.sleep(10);
            // 返回确认状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
