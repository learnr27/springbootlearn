package com.bannad927.queue;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;
import java.util.UUID;

/**
 * 延迟投递和定时投递
 * @author: chengbinbin
 * @date: 2021.1.26
 **/
@Slf4j
public class JmsScheduleProduce {
    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    private static final String ACTIVEMQ_QUEUE_NAME = "Schedule";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(ACTIVEMQ_QUEUE_NAME);
        MessageProducer messageProducer = session.createProducer(queue);

        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);   // 持久化  如果开启
        long delay = 10 * 1000;
        long perid = 5 * 1000;
        int repeat = 3;

        try {
            for (int i = 0; i < 3; i++) {
                TextMessage textMessage = session.createTextMessage("Schedule tx msg--" + i);
                textMessage.setJMSMessageID(UUID.randomUUID().toString() + "Schedule");

                // 消息每过 3 秒投递，每 4 秒重复投递一次 ，一共重复投递 7 次
                // 延迟的时间
                textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
                // 重复投递的时间间隔
                textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, perid);
                // 重复投递的次数
                textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
                // 此处的意思：该条消息，等待10秒，之后每5秒发送一次，重复发送3次。
                messageProducer.send(textMessage);
            }
            log.info("消息发送完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            messageProducer.close();
            session.close();
            connection.close();
        }
    }

}
