package com.bannad927.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Topic;
import java.util.UUID;

/**
 * @author: chengbinbin
 * @date: 2021.1.25
 **/
@Component
@Slf4j
public class TopicProduce {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Topic topic;

   // @Scheduled(fixedDelay = 3000)
    public void produceTopic() {
        String id = UUID.randomUUID().toString().substring(0, 6);
        jmsMessagingTemplate.convertAndSend(topic, "Topic主题消息" + id);
        log.info("生产者发布的主题:{}", id);

    }
}
