package com.feifei.rabbitmq.demo.consumer;

import com.feifei.rabbitmq.common.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.DEMO_QUEUE;
import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.FANOUT_QUEUE_1;

/**
 * 测试fanout模式下的消费者
 *
 * @author shixiongfei
 * @date 2019-12-05
 * @since
 */
public class FanoutExchangeConsumer {

    public static void main(String[] args) throws Exception {
        // 创建队列
        Connection connection = ConnectionUtil.getConnection();
        // 创建一个通道, 和客户端交互不直接通过连接，而是channel,可理解为一个轻量级的connection
        IntStream.range(1,3).forEach(i -> {
            try {
                Channel channel = connection.createChannel();
                // 创建一个消费者实例，用于监听通道中是否有消息，存在则监听消费
                com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println(new String(body, "UTF-8"));
                        // 消息消费成功，手动通知mq服务剔除已经消费的消息
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                };

                // 消费指定队列, 这里取消自动确认，如果业务执行失败，则消息会出现丢失，建议采用手动确认
                channel.basicConsume(String.format("fanout_queue_%d", i), false, consumer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


//        channel.close();
//        connection.close();
    }
}
