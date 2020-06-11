package com.bannad927.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chengbb
 * @date 2020.6.10
 */
@Slf4j
public class SimpleSender {

    @Autowired
    private RabbitTemplate template;

    private static final String queueName = "simple.hello";

    /**
     * 生产者通过send方法向队列simple.hello中发送消息
     */
    public void send() {
        String message = "Hello World!";
        this.template.convertAndSend(queueName, message);
        log.info(" [x] Sent '{}'", message);
    }
}
