package com.feifei.rabbitmq.common.constants;

/**
 * mq虚拟主机常量类
 *
 * virtual host => 虚拟主机，我们可以认为是一个namespace
 * 在多个客户端访问mq服务时，可通过虚拟主机对其进行exchange, queue等的隔离
 *
 * @author shixiongfei
 * @date 2019-12-04
 * @since
 */
public class RabbitmqVirtualHostConstants {

    /**
     * feifei虚拟主机，用于飞飞集团
     */
    public static final String FEIFEI_VHOSTS = "feifei_vhosts";
}
