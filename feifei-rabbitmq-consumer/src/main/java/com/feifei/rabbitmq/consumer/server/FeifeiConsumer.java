package com.feifei.rabbitmq.consumer.server;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.FEIFEI_QUEUE;
import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.FEIFEI_QUEUE_1;

/**
 * 飞飞消费者
 *
 * @author shixiongfei
 * @date 2020-03-18
 * @since
 */
@Component
public class FeifeiConsumer {


    @RabbitListener(queues = FEIFEI_QUEUE, containerFactory = "simpleRabbitListenerContainerFactory")
    public void consume(Message message, Channel channel) throws IOException {
        System.out.println("消费飞飞队列成功, 获取的消息内容为:" + new String(message.getBody(), "UTF-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = FEIFEI_QUEUE_1)
    public void consume1(Message message) throws UnsupportedEncodingException {
        System.out.println("消费飞飞队列1成功, 获取的消息内容为:" + new String(message.getBody(), "UTF-8"));
    }

}
