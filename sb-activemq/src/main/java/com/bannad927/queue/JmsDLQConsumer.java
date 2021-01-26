package com.bannad927.queue;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;

/**
 * 死信队列
 *
 * @author: chengbinbin
 * @date: 2021.1.26
 **/
@Slf4j
public class JmsDLQConsumer {

    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    private static final String ACTIVEMQ_QUEUE_NAME = "DLQ";

    public static void main(String[] args) throws Exception {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(3);
        //  当你第四次消费消息的时候就会将消息加入死信队列

        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);

        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();
        //消费者。开启事务，却没有commit。重启消费者，前3次都能收到消息，到第4次，不会再收到消息
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(ACTIVEMQ_QUEUE_NAME);
        MessageConsumer messageConsumer = session.createConsumer(queue);

        messageConsumer.setMessageListener(new MessageListener() {

            @Override
            public void onMessage(Message message) {
                try {
                    if (null != message && message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        log.info("死信队列cluster msg:{}", textMessage.getText());
                       // session.commit();
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
