package com.feifei.rabbitmq.demo.producer;

import com.feifei.rabbitmq.common.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.ExchangeTypes;

import static com.feifei.rabbitmq.common.constants.RabbitmqExchangeConstants.FANOUT_EXCHANGE;
import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.*;

/**
 * 测试fanout类型的交换机消息生产者
 * fanout交换机和路由key是没有关系的，它只关注与此交换机所绑定的队列有哪些
 * 生产者将消息发送到mq之前，会先到交换机，此模式下的交换机会把消息通知广播分发
 * 它所绑定的所有队列中
 *
 * @author shixiongfei
 * @date 2019-12-05
 * @since
 */
public class FanoutExchangeProducer {

    public static void main(String[] args) throws Exception {
        // 创建队列
        Connection connection = ConnectionUtil.getConnection();
        // 创建一个通道, 和客户端交互不直接通过连接，而是channel,可理解为一个轻量级的connection
        Channel channel = connection.createChannel();

        // 创建3个队列，然后与交换机进行绑定
        channel.queueDeclare(FANOUT_QUEUE_1, true, false,false, null);
        channel.queueDeclare(FANOUT_QUEUE_2, true, false,false, null);
        channel.queueDeclare(FANOUT_QUEUE_3, true, false,false, null);

        // 创建交换机和设置交换机的类型
        channel.exchangeDeclare(FANOUT_EXCHANGE, ExchangeTypes.FANOUT);
        // 将当前交换机和多个队列进行绑定
        channel.queueBind(FANOUT_QUEUE_1, FANOUT_EXCHANGE, "");
        channel.queueBind(FANOUT_QUEUE_2, FANOUT_EXCHANGE, "");
        channel.queueBind(FANOUT_QUEUE_3, FANOUT_EXCHANGE, "");
        // 向mq推送消息, 需要指定发送到哪个交换机，路由key, 路由头的相关信息，消息内容
        channel.basicPublish(FANOUT_EXCHANGE,"", null, "hello, rabbitmq".getBytes());

        System.out.println("消息推送成功");
        // 关闭channel
        channel.close();
        // 关闭连接
        connection.close();

    }
}
