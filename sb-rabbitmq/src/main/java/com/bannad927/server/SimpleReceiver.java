package com.bannad927.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author chengbb
 * @date 2020.6.10
 */
@RabbitListener(queues = "simple.hello")
@Slf4j
public class SimpleReceiver {

    /**
     * 消费者从队列simple.hello中获取消息
     *
     * @param in
     */
    @RabbitHandler
    public void receive(String in) {
        log.info(" [x] Received '{}'", in);
    }
}
