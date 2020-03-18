package com.feifei.rabbitmq.common.exchange;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.feifei.rabbitmq.common.constants.RabbitmqExchangeConstants.*;

/**
 * @author shixiongfei
 * @date 2019-12-08
 * @since
 */
@Configuration
public class RabbitmqExchange {

    /**
     * 创建备用交换机
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Bean(name = SLAVE_EXCHANGE)
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(SLAVE_EXCHANGE, true, false, null);
    }

    /**
     * 创建主交换机
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Bean(name = MASTER_EXCHANGE)
    public DirectExchange directExchange() {
        Map<String, Object> params = new HashMap<>(1);
        params.put(ALTERNATE_EXCHANGE, SLAVE_EXCHANGE);
        return new DirectExchange(MASTER_EXCHANGE, true, false, params);
    }

    @Bean(name = DEFAULT_EXCHANGE)
    public DirectExchange defaultExchange() {
        return new DirectExchange(DEFAULT_EXCHANGE, true, false, null);
    }

    @Bean(name = DEAD_LETTER_EXCHANGE)
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE, true, false, null);
    }

    /**
     * 飞飞交换机，进行测试
     *
     * @author shixiongfei
     * @date 2020-03-18
     * @updateDate 2020-03-18
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Bean(name = FEIFEI_EXCHANGE)
    public DirectExchange feifeiExchange() {
        return new DirectExchange(FEIFEI_EXCHANGE, true, false, null);
    }
}