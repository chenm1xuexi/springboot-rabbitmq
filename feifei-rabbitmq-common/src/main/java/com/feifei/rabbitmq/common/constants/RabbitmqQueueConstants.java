package com.feifei.rabbitmq.common.constants;

/**
 * @author shixiongfei
 * @date 2019-12-03
 * @since
 */
public class RabbitmqQueueConstants {

    /**
     * 定义一个测试队列名称
     */
    public static final String MSG_QUEUE = "msg_queue";

    /**
     * 定义一个演示的队列名称
     */
    public static final String DEMO_QUEUE = "demo_queue";

    /**
     * 默认的队列 routingKey
     */
    public static final String DEFAULT_QUEUE = "default_queue";

    // fanout queue

    /**
     * 测试fanout类型下的队列1
     */
    public static final String FANOUT_QUEUE_1 = "fanout_queue_1";

    /**
     * 测试fanout类型下的队列2
     */
    public static final String FANOUT_QUEUE_2 = "fanout_queue_2";

    /**
     * 测试fanout类型下的队列3
     */
    public static final String FANOUT_QUEUE_3 = "fanout_queue_3";

    // direct queue

    /**
     * 测试direct类型下的队列1
     */
    public static final String DIRECT_QUEUE_1 = "direct_queue_1";

    /**
     * 测试direct类型下的队列2
     */
    public static final String DIRECT_QUEUE_2 = "direct_queue_2";

    /**
     * 测试direct类型下的队列3
     */
    public static final String DIRECT_QUEUE_3 = "direct_queue_3";

    public static final String SPARE_QUEUE = "spare_queue";

    /**
     * 定义一个死信队列
     */
    public static final String DEAD_LETTER_QUEUE = "dead_letter_queue";

    /**
     * 定义一个逻辑队列，此队列来绑定死信交换机
     */
    public static final String LOGIC_QUEUE = "logic_queue";

    /**
     * 飞飞队列
     */
    public static final String FEIFEI_QUEUE = "feifei-queue";

    /**
     * 飞飞队列1
     */
    public static final String FEIFEI_QUEUE_1 = "feifei-queue-1";
}