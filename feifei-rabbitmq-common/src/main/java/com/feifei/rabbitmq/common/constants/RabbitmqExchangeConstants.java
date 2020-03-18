package com.feifei.rabbitmq.common.constants;

/**
 * rabbitmq的交换机常量工具类
 * exchange => 交换机， 用于路由消息到具体的消息队列
 * bind => 将队列和交换机进行绑定
 * routingKey => 绑定的key
 * 生产者指定routingKey，将消息发送到指定的broker,broker通过交换机来路由routingKey
 * routingKey匹配成功，则发送到指定的消息队列queue中
 *
 *
 * 如果未指定交换机，则mq会自动生成一个交换机 AMQP(default)
 *
 * @author shixiongfei
 * @date 2019-12-04
 * @since
 */
public class RabbitmqExchangeConstants {

    /**
     * 定义一个默认的交换机
     */
    public static final String DEFAULT_EXCHANGE = "default_exchange";

    /**
     * 定义一个fanout类型的交换机
     */
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    /**
     * 定义一个direct类型的交换机
     */
    public static final String DIRECT_EXCHANGE = "direct_exchange";

    public static final String SLAVE_EXCHANGE = "slave_exchange";

    public static final String MASTER_EXCHANGE = "master_exchange";

    /**
     * 备份交换机key
     */
    public static final String ALTERNATE_EXCHANGE = "alternate-exchange";

    /**
     * 死信交换机
     */
    public static final String DEAD_LETTER_EXCHANGE = "dead_letter_exchange";

    /**
     * 死信交换机key, 用于与队列绑定
     */
    public static final String DEAD_LETTER_EXCHANGE_KEY = "x-dead-letter-exchange";

    /**
     * 飞飞测试交换机
     */
    public static final String FEIFEI_EXCHANGE = "feifei-exchange";


}