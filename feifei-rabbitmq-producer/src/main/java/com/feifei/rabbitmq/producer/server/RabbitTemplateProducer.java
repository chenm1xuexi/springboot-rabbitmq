package com.feifei.rabbitmq.producer.server;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.feifei.rabbitmq.common.constants.RabbitmqExchangeConstants.*;
import static com.feifei.rabbitmq.common.constants.RabbitmqRoutingKeyConstants.*;

/**
 * rabbitmqTemplate生产者
 *
 * 如何保证发送方确认
 * 需要在生产者 -> 交换机 + 交换机 -> 队列配置消息确认机制（也可采用事务，但是不推荐）
 * 并配置相关的消息回调(都为异步)
 *
 * @author shixiongfei
 * @date 2019-12-05
 * @since
 */
@Component
public class RabbitTemplateProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * {@link PostConstruct 该表示在当前类注册到Spring容器的时候进行的前置处理}
     */
    @PostConstruct
    public void init() {
        // 设置自己需要的确认回调机制，这里需要将rabbitTemplate设置为多例，而非单例
        // rabbitTemplate.setConfirmCallback();
    }

    /**
     * 采用rabbitTemplate进行消息发送
     * 其中会带上部分元数据correlationData
     *
     * @author shixiongfei
     * @date 2019-12-05
     * @updateDate 2019-12-05
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    public void send() {
        // 定义消息的唯一标识
        CorrelationData correlationData = new CorrelationData("订单id");
        // 推送消息
        // 定义推送消息的消息体内容
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, "direct_1", "go shopping", correlationData);
    }

    /**
     * 采用rabbitTemplate进行消息发送
     * 测试消息在rabbitmq中的exchange => queue出现错误时的回调
     *
     * @author shixiongfei
     * @date 2019-12-05
     * @updateDate 2019-12-05
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    public void sendQueue() {
        // 定义消息的唯一标识
        CorrelationData correlationData = new CorrelationData("订单id");
        // 定义推送消息的消息体内容
        Map<String ,Object> map = new HashMap<>(2);
        map.put("name", "飞飞");
        map.put("age", 23);

        // 推送消息
        rabbitTemplate.convertAndSend(MASTER_EXCHANGE, TEST_SPARE_ROUTING_KEY, map, correlationData);
    }

    /**
     * 采用rabbitTemplate进行消息发送
     *
     * @author shixiongfei
     * @date 2019-12-05
     * @updateDate 2019-12-05
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    public void send2() {
        // 定义消息的唯一标识
        // 推送消息
        // 定义推送消息的消息体内容
        rabbitTemplate.convertAndSend(DEFAULT_EXCHANGE, DEFAULT_ROUTING_KEY, "hello,consumer");
    }

    /**
     * 采用rabbitTemplate进行消息发送
     * 测试私信队列
     * @author shixiongfei
     * @date 2019-12-05
     * @updateDate 2019-12-05
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    public void send3() {
        // 定义消息的唯一标识
        // 推送消息
        // 定义推送消息的消息体内容
        rabbitTemplate.convertAndSend(DEFAULT_EXCHANGE, LOGIC_ROUTING_KEY, "hello,dead letter queue");
    }

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
    public void sendMsg1() {
        LocalDate now = LocalDate.now();
        System.out.println("生产者发送消息:" + now.toString());
        // 发送到默认交换机上绑定的路由id指向的队列
        rabbitTemplate.convertAndSend(FEIFEI_EXCHANGE, FEIFEI_ROUTING_KEY, now.toString());
    }


}