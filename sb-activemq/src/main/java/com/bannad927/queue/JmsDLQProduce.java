package com.bannad927.queue;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 死信队列
 *
 * @author: chengbinbin
 * @date: 2021.1.26
 **/
@Slf4j
public class JmsDLQProduce {

    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    private static final String ACTIVEMQ_QUEUE_NAME = "DLQ";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(ACTIVEMQ_QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queue);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for (int i = 1; i < 4; i++) {
            TextMessage textMessage = session.createTextMessage("DLQ msg--" + i);
            messageProducer.send(textMessage);
        }
        log.info("消息发送到MQ完成");
        messageProducer.close();
        session.close();
        connection.close();
    }

}
