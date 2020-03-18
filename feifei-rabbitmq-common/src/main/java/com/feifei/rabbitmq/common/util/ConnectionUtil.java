package com.feifei.rabbitmq.common.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

import static com.feifei.rabbitmq.common.constants.RabbitmqVirtualHostConstants.FEIFEI_VHOSTS;

/**
 * 连接rabbitmq服务工具类
 *
 * @author shixiongfei
 * @date 2019-12-04
 * @since
 */
public class ConnectionUtil {

    /**
     * 创建一个和mq服务的连接
     *
     * @author shixiongfei
     * @date 2019-12-04
     * @updateDate 2019-12-04
     * @updatedBy shixiongfei
     * @param
     * @return
     */
    public static Connection getConnection() throws Exception {
        // 创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 配置mq服务主机地址
        connectionFactory.setHost("127.0.0.1");
        // 配置端口号，连接用户名和虚拟主机等
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost(FEIFEI_VHOSTS);
        connectionFactory.setUsername("feifei");
        connectionFactory.setPassword("feifei");
        return connectionFactory.newConnection();
    }
}