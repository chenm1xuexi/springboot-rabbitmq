package com.feifei.rabbitmq.common.factory;

import org.springframework.amqp.core.Queue;

/**
 * @author shixiongfei
 * @date 2019-12-03
 * @since
 */
public interface QueueFactory {

    /**
     * 通过队列名来获取指定队列
     *
     * @author shixiongfei
     * @date 2019-12-03
     * @updateDate 2019-12-03
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    Queue getQueue(String name);
}