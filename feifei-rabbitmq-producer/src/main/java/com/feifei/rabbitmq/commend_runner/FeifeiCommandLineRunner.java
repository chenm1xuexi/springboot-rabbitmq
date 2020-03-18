package com.feifei.rabbitmq.commend_runner;

import com.feifei.rabbitmq.producer.server.RabbitTemplateProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

/**
 * @author shixiongfei
 * @date 2019-12-05
 * @since
 */
@Component
public class FeifeiCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RabbitTemplateProducer rabbitTemplateProducer;

    @Override
    public void run(String... args) {
        System.out.println("开始执行消息推送");
        IntStream.range(0,3).forEach( i -> rabbitTemplateProducer.sendMsg1());
        System.out.println("消息推送成功");
    }
}
