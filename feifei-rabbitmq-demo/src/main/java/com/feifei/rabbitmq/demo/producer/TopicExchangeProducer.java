package com.feifei.rabbitmq.demo.producer;

/**
 * 测试topic类型下的交换机生产者
 * topic类型 将路由键和某模式进行匹配
 *
 * 我们可以理解为每个队列都有其关注的主题（topic）
 * 所有的消息都会带有一个标题（routeKey）
 * 标题属于主题的子，队列关注的是主题中的标题，存到多个队列都关注一个标题
 * 所以此类型下的exchange需要支持标题的模糊匹配，以达到将消息同时发送给自己关注的队列集合中
 *
 * @author shixiongfei
 * @date 2019-12-05
 * @since
 */
public class TopicExchangeProducer {

    public static void main(String[] args) {
        // TODO 暂不演示
    }
}
