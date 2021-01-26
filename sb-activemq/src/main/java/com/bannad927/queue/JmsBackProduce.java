package com.bannad927.queue;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

/**
 * 带接收回调的异步发送
 * AsyncCallback
 *
 * @author: chengbinbin
 * @date: 2021.1.26
 **/
@Slf4j
public class JmsBackProduce {

    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    private static final String ACTIVEMQ_QUEUE_NAME = "AsyncCallback";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(ACTIVEMQ_QUEUE_NAME);
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);
        try {
            for (int i = 0; i < 3; i++) {
                TextMessage textMessage = session.createTextMessage("AsyncCallback msg--" + i);
                textMessage.setJMSMessageID(UUID.randomUUID().toString() + "AsyncCallback");
                final String msgId = textMessage.getJMSMessageID();

                activeMQMessageProducer.send(textMessage, new AsyncCallback() {

                    @Override
                    public void onSuccess() {
                        log.info("成功发送消息Id:{}", msgId);
                    }

                    @Override
                    public void onException(JMSException e) {
                        log.info("失败发送消息Id:{}", msgId);
                    }
                });
            }
            log.info("消息发送完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            activeMQMessageProducer.close();
            session.close();
            connection.close();
        }
    }

}
