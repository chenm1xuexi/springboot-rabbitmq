package com.feifei.rabbitmq.consumer.springboot_use;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.feifei.rabbitmq.common.constants.RabbitmqQueueConstants.*;

/**
 * rabbitmq的消息消费机制是消息队列将积累的消息直接一次性发送给消费者进行消费
 *
 * @author shixiongfei
 * @date 2019-12-08
 * @since
 */
@Component
public class SpringbootConsumer {

    static int i = 0;

    static long startTime = 0;

    /**
     * 配置了手动确认消息消费是否成功，如果不返回ack，则默认为为未消费成功
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    // @RabbitListener(queues = DEFAULT_QUEUE, containerFactory = "simpleRabbitListenerContainerFactory")
    public void consume(Message message, Channel channel) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("消费消息：" + new String(message.getBody(), "UTF-8"));
        System.out.println("获取当前消息的唯一标识:" + message.getMessageProperties().getDeliveryTag());
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        // 设置预取值，如果不设置，消息队列默认会现将消息全部推送过来，让消费者一个个消费
        // 设置一次消费5个，消费完成再去消息队列中获取
        channel.basicQos(1);
        // 当设置QOS的值适当的时候，如果消息消费失败则只需要进行一次消息确认，此时是批量消息确认
        // 提高整体系统的性能
        // 处理相关的业务逻辑
        if (handleBusinessLogic()) {
            // 业务逻辑处理成功, 返回当前的唯一标识，并为非批量消息确认
            channel.basicAck(deliveryTag, false);
        } else {
            // 业务逻辑处理失败，则需要将消息返还给消息队列，下一次再次进行重试消费(可支持批量撤回)
            channel.basicNack(deliveryTag, false, true);
            // 只可支持单个退回，但现在基本不会用这个
            // channel.basicReject(deliveryTag, true);
        }
    }


    public boolean handleBusinessLogic() {
        return true;
    }


    /**
     * 配置了手动确认消息消费是否成功，如果不返回ack，则默认为为未消费成功
     *
     * 测试批量消费，这可能会出现消息重复消费的问题，
     * 可在业务逻辑中进行一个字段来标识消息是否消费，1代表消息，则mq下次推送此消息过来时
     * 首先要校验是否消费，如果消费完成，则不进行消费，返回ack告诉mq已消费成功
     *
     * 经过测试好像我这个最多一次批量处理250个，如果预取值要大于此值，则无效，只会消费250，且未返回通知
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    // @RabbitListener(queues = DEFAULT_QUEUE, containerFactory = "simpleRabbitListenerContainerFactory")
    public void consume1(Message message, Channel channel) throws Exception {
        channel.basicQos(250);
        if (i == 0) {
            startTime = System.currentTimeMillis();
        }
//        System.out.println("消费消息：" + new String(message.getBody(), "UTF-8"));
//        System.out.println("获取当前消息的唯一标识:" + message.getMessageProperties().getDeliveryTag());
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        // 设置预取值，如果不设置，消息队列默认会现将消息全部推送过来，让消费者一个个消费
        // 设置一次消费5个，消费完成再去消息队列中获取
        // channel.basicAck(deliveryTag, false);
        // 当设置QOS的值适当的时候，如果消息消费失败则只需要进行一次消息确认，此时是批量消息确认
        // 提高整体系统的性能
        //
        // if (i >= 20000) {

        i++;
        System.out.println("i = " + i);
         if (i % 250 == 0 || i == 6000) {
            // 业务逻辑处理成功, 返回当前的唯一标识，并为非批量消息确认
             channel.basicAck(deliveryTag, true);
             System.out.println("耗时为：" + (System.currentTimeMillis() - startTime));
        }
    }

    /**
     * 测试将消费失败的消息存于死信交换机下的死信队列中
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @RabbitListener(queues = LOGIC_QUEUE, containerFactory = "simpleRabbitListenerContainerFactory")
    public void consumeLogicMsg(Message message, Channel channel) throws Exception {
        System.out.println("消费消息：" + new String(message.getBody(), "UTF-8"));
        System.out.println("获取当前消息的唯一标识:" + message.getMessageProperties().getDeliveryTag());
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        // 这里直接返回消费失败，因为要测试是否消息会存入死信队列中
        // requeue设置为false,代表不会回滚到原队列中
        channel.basicNack(deliveryTag, false, false);
        System.out.println("逻辑队列拒绝了消息，将消息存入死信队列中");
    }


    /**
     * 监听死信队列中的信息
     *
     * 死信交换机 + 死信队列的应用场景:
     * 超时订单 比如说订单10分钟内未支付则超时，（队列可以设置超时时间）此时的订单队列中会将
     * 超时的订单加入到死信队列中（超时订单队列）然后进行相应的处理，这样就可以将有效订单和无效
     * 的订单进行了隔离。实现的对应的代码的解耦合
     *
     * @author shixiongfei
     * @date 2019-12-08
     * @updateDate 2019-12-08
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    @RabbitListener(queues = DEAD_LETTER_QUEUE, containerFactory = "simpleRabbitListenerContainerFactory")
    public void consumeDeadLetterMsg(Message message, Channel channel) throws Exception {
        System.out.println("消费消息：" + new String(message.getBody(), "UTF-8"));
        System.out.println("获取当前消息的唯一标识:" + message.getMessageProperties().getDeliveryTag());
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        // 通知mq死信消息消费成功
        channel.basicAck(deliveryTag, false);
        System.out.println("死信队列接收到了逻辑队列消费失败的消息");
    }

}
