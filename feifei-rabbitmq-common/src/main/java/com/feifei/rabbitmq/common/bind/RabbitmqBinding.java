package com.feifei.rabbitmq.common.bind;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.feifei.rabbitmq.common.constants.RabbitmqExchangeConstants.*;
import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.*;
import static com.feifei.rabbitmq.common.constants.RabbitmqRoutingKeyConstants.*;

/**
 * 队列绑定配置类
 *
 * @author shixiongfei
 * @date 2019-12-08
 * @since
 */
@Configuration
public class RabbitmqBinding {

    /**
     * 将备用交换机绑定执行队列
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Bean
    public Binding bindFanoutExchange(@Qualifier(SPARE_QUEUE) Queue queue, @Qualifier(SLAVE_EXCHANGE) FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindDirectExchange(@Qualifier(SPARE_QUEUE) Queue queue, @Qualifier(MASTER_EXCHANGE) DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TEST_SPARE_ROUTING_KEY);
    }

    @Bean
    public Binding bindDefaultExchange(@Qualifier(DEFAULT_QUEUE) Queue queue, @Qualifier(DEFAULT_EXCHANGE) DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEFAULT_ROUTING_KEY);
    }

    /**
     * 将逻辑队列和默认交换机进行绑定，绑定的路由为LOGIC_ROUTING_KEY
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Bean
    public Binding bindLogic2DefaultExchange(@Qualifier(LOGIC_QUEUE) Queue queue, @Qualifier(DEFAULT_EXCHANGE) DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(LOGIC_ROUTING_KEY);
    }


    /**
     * 将死信队列和死信交换机进行绑定
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Bean
    public Binding bindDeadLetterExchange(@Qualifier(DEAD_LETTER_QUEUE) Queue queue, @Qualifier(DEAD_LETTER_EXCHANGE) DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_ROUTING_KEY);
    }

    /**
     * 将飞飞交换机和飞飞队列进行绑定，绑定条件是飞飞路由key
     *
     * @author shixiongfei
     * @date 2020-03-18
     * @updateDate 2020-03-18
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Bean
    public Binding binfFeifeiExchange(@Qualifier(FEIFEI_QUEUE) Queue queue, @Qualifier(FEIFEI_EXCHANGE) DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(FEIFEI_ROUTING_KEY);
    }

    /**
     * 将飞飞交换机和飞飞队列进行绑定，绑定条件是飞飞路由key
     *
     * @author shixiongfei
     * @date 2020-03-18
     * @updateDate 2020-03-18
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @Bean
    public Binding binfFeifeiExchange1(@Qualifier(FEIFEI_QUEUE_1) Queue queue, @Qualifier(FEIFEI_EXCHANGE) DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(FEIFEI_ROUTING_KEY);
    }
}