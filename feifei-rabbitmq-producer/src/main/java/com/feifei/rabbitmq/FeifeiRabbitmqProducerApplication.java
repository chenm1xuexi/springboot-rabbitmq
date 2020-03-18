package com.feifei.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消息生产者微服务（实际场景为每个服务既是生产者也是消费者）
 * 我们需要定义一个消息微服务
 *
 * @author shixiongfei
 * @date 2019-12-03
 * @since
 */
@SpringBootApplication
public class FeifeiRabbitmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeifeiRabbitmqProducerApplication.class, args);
    }
}