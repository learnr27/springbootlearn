package com.bannad927.queue;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * queue消费者
 *
 * @author: chengbinbin
 * @date: 2021.1.25
 **/
@Slf4j
public class JmsConsumer {

    //  IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "nio://127.0.0.1:61608";
    public static final String QUEUE_NAME = "nioqueue01";

    public static void main(String[] args) throws JMSException, IOException {
        // 1 按照给定的url创建连接工程，这个构造器采用默认的用户名密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂连接 connection  和 启动
        Connection connection = activeMQConnectionFactory.createConnection();
        //  启动
        connection.start();
        // 3 创建回话  session
        // 两个参数，第一个事务， 第二个签收
        // 开启是否 true 需要添加 session.commit();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4 创建目的地 （两种 ： 队列/主题   这里用队列）
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5 创建消息的消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);

      /*  //同步阻塞方式reveive()   空参数的receive方法是阻塞，有参数的为等待时间
        //订阅者或消费者使用MessageConsumer 的receive() 方法接收消息，receive 在接收之前一直阻塞
        while (true) {
            // 这里是 TextMessage 是因为消息发送者是 TextMessage ， 接受处理的
            // 也应该是这个类型的消息
            TextMessage textMessage = (TextMessage) messageConsumer.receive(1000L);
            if (null != textMessage) {
                log.info("消费者的消息:{}", textMessage.getText());
            } else {
                break;
            }
        }*/


        messageConsumer.setMessageListener(message -> {
            if (null != message && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    log.info("queue消费者的消息:{}", textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 保证控制台不灭  不然activemq 还没连上就关掉了连接
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
