package com.feifei.rabbitmq.producer.server;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.feifei.rabbitmq.common.constants.RabbitmqExchangeConstants.FEIFEI_EXCHANGE;
import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.MSG_QUEUE;
import static com.feifei.rabbitmq.common.constants.RabbitmqRoutingKeyConstants.FEIFEI_ROUTING_KEY;

/**
 * 消息生产者配置类
 *
 * @author shixiongfei
 * @date 2019-12-03
 * @since
 */
@Component
public class FeifeiProducter {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 生产生产消息
     *
     * @author shixiongfei
     * @date 2019-12-03
     * @updateDate 2019-12-03
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    public void sendMsg() {
        LocalDate now = LocalDate.now();
        System.out.println("生产者发送消息:" + now.toString());
        // 发送到默认交换机上绑定的路由id指向的队列
        amqpTemplate.convertAndSend(MSG_QUEUE, now.toString());
    }

}