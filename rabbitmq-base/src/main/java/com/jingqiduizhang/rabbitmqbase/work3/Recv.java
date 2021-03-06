package com.jingqiduizhang.rabbitmqbase.work3;


import com.jingqiduizhang.rabbitmqbase.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者一
 * rabbitMq work 模式  一对多发送  1个发送 多个接收 相互抢数据
 * 能者多劳
 *
 * 手动模式
 *    // 监听队列，手动返回完成  false 为手动模式
 *    channel.basicConsume(QUEUE_NAME, false, consumer);
 *   // 返回确认状态 执行完成要返回确认
 *   channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
 */
public class Recv {
    private static int count = 1;
    private final static String QUEUE_NAME = "test_queue_work";
    private static final long sleepTime = 10;
    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者 在消费完成之后再去要信息 消费的快了就会工作的多
        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            count ++;
            System.out.println("工作消耗时间: "+ sleepTime +" 工作个数: "+count+" [x] Received '" + message + "'");
            //休眠
            Thread.sleep(sleepTime);
            // 返回确认状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
