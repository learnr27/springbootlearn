package com.bannad927.topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * topic生产中
 * @author: chengbinbin
 * @date: 2021.1.25
 **/
@Slf4j
public class JmsProduceTopic {

    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        // 1 按照给定的url创建连接工程，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 设置允许有数据丢失
        activeMQConnectionFactory.setUseAsyncSend(true);
        // 2 通过连接工厂连接 connection
        Connection connection = activeMQConnectionFactory.createConnection();
        //  启动
        connection.start();
        // 3 创建回话  session
        // 两个参数，第一个事务， 第二个签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地 （两种 ： 队列/主题   这里用队列）
        Topic topic = session.createTopic(TOPIC_NAME);

        // 5 创建消息的生产者
        MessageProducer messageProducer = session.createProducer(topic);
        // 非持久化消息 和持久化消息演示
        //持久化  如果开启  就会存入文件或数据库中
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 6 通过messageProducer 生产 3 条 消息发送到消息队列中
        for (int i = 0; i < 3; i++) {
            // 7  创建字消息
            TextMessage textMessage = session.createTextMessage("topic message----" + i);
            // 8  通过messageProducer发布消息
            messageProducer.send(textMessage);
        }
        // 9 关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        log.info("消息发送到MQ完成");

    }
}
