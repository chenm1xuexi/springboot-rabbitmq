package com.feifei.rabbitmq.common.factory;

import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

/**
 * 通用queue创建工厂
 *
 * @author shixiongfei
 * @date 2019-12-03
 * @since
 */
@Component
public class CommonQueueFactory implements QueueFactory {

    @Override
    public Queue getQueue(String name) {
        return new Queue(name);
    }
}