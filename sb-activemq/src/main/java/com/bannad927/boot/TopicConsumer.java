package com.bannad927.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * @author: chengbinbin
 * @date: 2021.1.25
 **/
@Component
@Slf4j
public class TopicConsumer {

    @JmsListener(destination = "${mytopic}")
    public void receive(TextMessage textMessage) throws  Exception{
        log.info("消费者收到订阅的主题:{}", textMessage.getText());
    }
}
