package com.feifei.rabbitmq.consumer.server;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.*;

/**
 * 测试消息队列消费者
 * {@link RabbitListener 用于监听MSG_QUEUE队列的消息，存在则消息}
 *
 * 所以说此消费模式为端-端的消费，消费者主动去拉取消息队列中的消息进行消费
 *
 * @author shixiongfei
 * @date 2019-12-03
 * @since
 */
@Component
// @RabbitListener(queuesToDeclare = {@Queue(SPARE_QUEUE)})
public class TestQueueConsumer {

    /**
     * 消费者进行消息消费
     * {@link RabbitHandler 指定监听到消息后交由此方法进行处理}
     *
     * @author shixiongfei
     * @date 2019-12-03
     * @updateDate 2019-12-03
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @RabbitListener(queues = SPARE_QUEUE)
    public void consume(String msg) {
        System.out.println("消息消费成功, 获取的消息内容为:" + msg);
    }

    /**
     * 消费者进行消息消费
     * {@link RabbitHandler 指定监听到消息后交由此方法进行处理}
     *
     * @author shixiongfei
     * @date 2019-12-03
     * @updateDate 2019-12-03
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @RabbitListener(queues = SPARE_QUEUE)
    public void consume(Message msg) {
        System.out.println("消息消费成功, 获取的消息内容为:" + msg);
    }
}
