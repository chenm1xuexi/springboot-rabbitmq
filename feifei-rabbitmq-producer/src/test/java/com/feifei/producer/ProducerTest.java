package com.feifei.producer;

import com.feifei.rabbitmq.FeifeiRabbitmqProducerApplication;
import com.feifei.rabbitmq.producer.server.FeifeiProducter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

/**
 * @author shixiongfei
 * @date 2019-12-03
 * @since
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FeifeiRabbitmqProducerApplication.class})
public class ProducerTest {

    @Autowired
    private FeifeiProducter producer;

    @Test
    public void testSendMsg() {
        IntStream.range(0,9)
                .forEach(i -> {
                    System.out.println(String.format("第%d生产者开始发送数据到mq", i + 1));
                    producer.sendMsg();
                });
    }
}
