package com.feifei.rabbitmq.demo.producer;

import com.feifei.rabbitmq.common.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.DEMO_QUEUE;

/**
 * 测试生产者
 *
 * @author shixiongfei
 * @date 2019-12-04
 * @since
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        // 创建队列
        Connection connection = ConnectionUtil.getConnection();
        // 创建一个通道, 和客户端交互不直接通过连接，而是channel,可理解为一个轻量级的connection
        Channel channel = connection.createChannel();
        channel.queueDeclare(DEMO_QUEUE, true, false, false, null);

        // 向mq推送消息, 需要指定发送到哪个交换机，路由key, 路由头的相关信息，消息内容
        channel.basicPublish("", DEMO_QUEUE, null, "hello, rabbitmq".getBytes());

        System.out.println("消息推送成功");
        // 关闭channel
        channel.close();
        // 关闭连接
        connection.close();

    }

}