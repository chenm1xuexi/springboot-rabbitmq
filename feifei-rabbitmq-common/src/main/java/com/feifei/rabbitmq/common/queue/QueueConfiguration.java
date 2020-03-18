package com.feifei.rabbitmq.common.queue;

import com.feifei.rabbitmq.common.factory.CommonQueueFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.HashMap;
import java.util.Map;

import static com.feifei.rabbitmq.common.constants.RabbitmqExchangeConstants.DEAD_LETTER_EXCHANGE;
import static com.feifei.rabbitmq.common.constants.RabbitmqExchangeConstants.DEAD_LETTER_EXCHANGE_KEY;
import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.*;
import static com.feifei.rabbitmq.common.constants.RabbitmqRoutingKeyConstants.DEAD_LETTER_ROUTING_KEY;
import static com.feifei.rabbitmq.common.constants.RabbitmqRoutingKeyConstants.X_DEAD_LETTER_ROUTING_KEY;


/**
 * @author shixiongfei
 * @date 2019-12-03
 * @since
 */
@Configuration
public class QueueConfiguration {

    @Autowired
    private CommonQueueFactory commonQueueFactory;

    /**
     * 测试类消息队列
     * MSG_QUEUE可以理解为routingKey
     * 这里的exchange交换路由是采用默认的交换路由
     *
     * @param
     * @return
     * @author shixiongfei
     * @date 2019-12-04
     * @updateDate 2019-12-04
     * @updatedBy shixiongfei
     */
    @Bean
    public Queue getTestQueue() {
        return commonQueueFactory.getQueue(MSG_QUEUE);
    }

    @Bean(name = "test111")
    public Queue queue() {
        return commonQueueFactory.getQueue("test111");
    }

    @Bean(name = SPARE_QUEUE)
    public Queue getSpareQueue() {
        return new Queue(SPARE_QUEUE, true, false, false, null);
    }


    /**
     * 创建一个演示的队列
     *
     * @return
     * @author shixiongfei
     * @date 2019-12-04
     * @updateDate 2019-12-04
     * @updatedBy shixiongfei
     */
    @Bean
    public Queue getQueue() {
        /*
         * @param name routingKey 用于标识一个队列
         * @param durable 消息是否持久化
         * @param exclusive 是否为排他队列，也就是只可被一个连接消费
         * @param autoDelete 是否自动删除队列
         * @param arguments 队列配置集合类 配置队列的大小，过期时间等
         */
        return new Queue(DEMO_QUEUE, true, false, false, null);
    }

    @Bean(name = DEFAULT_QUEUE)
    public Queue getDefaultQueue() {
        return new Queue(DEFAULT_QUEUE, true, false, false, null);
    }

    /**
     * 创建逻辑队列，其中绑定了死信交换机
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Bean(name = LOGIC_QUEUE)
    public Queue getLogicQueue() {
        // 指定队列锁绑定的死信交换机,当此队列的消息被拒绝时，会直接将消息发送到死信交换机中
        Map<String, Object> map = new HashMap<>(1);
        map.put(DEAD_LETTER_EXCHANGE_KEY, DEAD_LETTER_EXCHANGE);
        // 定义死信交换机的路由key
        map.put(X_DEAD_LETTER_ROUTING_KEY, DEAD_LETTER_ROUTING_KEY);
        return new Queue(LOGIC_QUEUE, false, false, false, map);
    }

    /**
     * 创建死信队列
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Bean(name = DEAD_LETTER_QUEUE)
    public Queue getDeadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE, false, false, false, null);
    }

    @Bean(name = FEIFEI_QUEUE)
    public Queue getFeifeiQueue() {
        return new Queue(FEIFEI_QUEUE, true, false, false, null);
    }

    @Bean(name = FEIFEI_QUEUE_1)
    public Queue getFeifeiQueue1() {
        return new Queue(FEIFEI_QUEUE_1, true, false, false, null);
    }
}