package com.bannad927.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author: chengbinbin
 * @date: 2021.1.26
 **/
@Component
@Slf4j
public class RedisReceiver implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info("getBody:{}", new String(message.getBody()));
        log.info("getChannel:{}", new String(message.getChannel()));
    }
}
