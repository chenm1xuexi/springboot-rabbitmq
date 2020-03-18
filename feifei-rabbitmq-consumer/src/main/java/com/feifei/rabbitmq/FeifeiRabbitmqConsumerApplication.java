package com.feifei.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消息消费者微服务
 *
 * @author shixiongfei
 * @date 2019-12-03
 * @since
 */

@SpringBootApplication
public class FeifeiRabbitmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeifeiRabbitmqConsumerApplication.class, args);
    }
}