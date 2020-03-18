package com.feifei.rabbitmq.demo.producer;

import com.feifei.rabbitmq.common.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.ExchangeTypes;

import static com.feifei.rabbitmq.common.constants.RabbitmqExchangeConstants.DIRECT_EXCHANGE;
import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.*;

/**
 * direct类型的交换机生产者
 *
 * 该交换机类型强制进行routingKey的匹配，匹配成功则存在绑定的队列则生产或消费
 * @author shixiongfei
 * @date 2019-12-05
 * @since
 */
public class DirectExchangeProducer {

    public static void main(String[] args) throws Exception {
        // 创建队列
        Connection connection = ConnectionUtil.getConnection();
        // 创建一个通道, 和客户端交互不直接通过连接，而是channel,可理解为一个轻量级的connection
        Channel channel = connection.createChannel();

        // 创建3个队列
        channel.queueDeclare(DIRECT_QUEUE_1, true, false,false, null);
        channel.queueDeclare(DIRECT_QUEUE_2, true, false,false, null);
        channel.queueDeclare(DIRECT_QUEUE_3, true, false,false, null);

        // 创建direct类型的交换机
        channel.exchangeDeclare(DIRECT_EXCHANGE, ExchangeTypes.DIRECT);

        // 队列和交换机进行绑定, 通常routingKey基本都是用队列名称来代替
        channel.queueBind(DIRECT_QUEUE_1, DIRECT_EXCHANGE, "direct_1");
        channel.queueBind(DIRECT_QUEUE_2, DIRECT_EXCHANGE, "direct_2");
        channel.queueBind(DIRECT_QUEUE_3, DIRECT_EXCHANGE, "direct_3");

        // 向mq推送消息, 需要指定发送到哪个交换机，路由key, 路由头的相关信息，消息内容
        // 这里的routingKey必须和交换机绑定的队列的routingKey保持一致，不然消息不会推送到mq中
        channel.basicPublish(DIRECT_EXCHANGE, "direct_1", null, "hello, rabbitmq".getBytes());

        System.out.println("消息推送成功");
        // 关闭channel
        channel.close();
        // 关闭连接
        connection.close();

    }
}
