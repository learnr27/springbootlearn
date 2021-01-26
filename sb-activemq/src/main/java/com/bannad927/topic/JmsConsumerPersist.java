package com.bannad927.topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 持久化topic 的消息消费者
 *
 * @author: chengbinbin
 * @date: 2021.1.25
 **/
@Slf4j
public class JmsConsumerPersist {

    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic_persistent01";

    public static void main(String[] args) throws JMSException {
        // 1 按照给定的url创建连接工程，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂连接 connection
        javax.jms.Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("persistent01");

        // 3 创建回话  session
        // 两个参数，第一个事务， 第二个签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地 （两种 ： 队列/主题   这里用主题）
        Topic topic = session.createTopic(TOPIC_NAME);
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "remark...");

        // 5 发布订阅
        connection.start();

        // 一直等
        Message message = topicSubscriber.receive();
        while (null != message) {
            TextMessage textMessage = (TextMessage) message;
            log.info("收到的持久化 topic:{}", textMessage.getText());
            // message = topicSubscriber.receive(3000L);
        }

        session.close();
        connection.close();
    }
}
