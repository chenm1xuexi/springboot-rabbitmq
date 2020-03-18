package com.feifei.rabbitmq.common.config;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;

import static com.feifei.rabbitmq.common.constants.RabbitmqVirtualHostConstants.FEIFEI_VHOSTS;

/**
 *
 * @author shixiongfei
 * @date 2019-12-05
 * @since
 */
@Configuration
public class RabbitmqConfig {


    @Bean
    public ConnectionFactory connectionFactory() {
        // 创建一个连接工厂
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        // 配置mq服务主机地址
        connectionFactory.setHost("127.0.0.1");
        // 配置端口号，连接用户名和虚拟主机等
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost(FEIFEI_VHOSTS);
        connectionFactory.setUsername("feifei");
        connectionFactory.setPassword("feifei");
        // 开启发送方确认模式 producer -> exchange
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        // rabbitmq自定义确认回调机制,异步回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            // correlationData 消息的唯一标识符, 比如说下订单时设置的订单id,错误回调时可通过此参数获取此id
            // ack 确认，是否发送成功
            // cause 发送失败原因，方便用户自定义处理
            System.out.println("correlationData = " + correlationData);
            System.out.println("ack = " + ack);
            System.out.println("cause = " + cause);
        });

        // 开始失败回调， 这里的失败回调是在交换机 => 具体的队列 exchange => queue
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                // message => 发送的消息 以及 消息的配置
                System.out.println("message = " + message);
                // 状态码
                System.out.println("replyCode = " + replyCode);
                // 失败描述
                System.out.println("replyText = " + replyText);
                // 发起的交换机
                System.out.println("exchange = " + exchange);
                // 路由键
                System.out.println("routingKey = " + routingKey);


                // 自定义相关的失败逻辑
            }
        });

        rabbitTemplate.setMessageConverter(new MessageConverter() {
            // 发送消息时的消息转换
            @Override
            public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
                String msg = JSON.toJSONString(object);
                // 设置解析消息的相关请求头信息
                messageProperties.setContentType("text/xml");
                messageProperties.setContentEncoding("UTF-8");
                return new Message(msg.getBytes(), messageProperties);
            }

            // 接收消息时的消息转换
            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                try {
                    return new String(message.getBody(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return message;
            }
        });

        return rabbitTemplate;
    }

    /**
     * 消息监听器容器 相当于@RabbitmqListener注解
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
//    @Bean
////    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
////        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
////        container.setConnectionFactory(connectionFactory());
////        container.setMessageListener((message -> {
////
////        }));
////
////        // 设置是否自动确认,这里设置为手动确认
////        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
////        return container;
////   }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory());
        // 设置消费者消费消息时进行手动确认消息
        containerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return containerFactory;
    }



}
