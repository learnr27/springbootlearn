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
public class QueueConsumer {

    /**
     * 注解监听
     *
     * @param textMessage
     * @throws Exception
     */
    @JmsListener(destination = "${myqueue}")
    public void receive(TextMessage textMessage) throws Exception {
        log.info("消费者收到消息:{}", textMessage.getText());
    }
}
