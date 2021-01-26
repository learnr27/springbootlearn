package com.bannad927.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.UUID;

/**
 * @author: chengbinbin
 * @date: 2021.1.25
 **/
@Component
@Slf4j
public class QueueProduce {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue queue;

    /**
     * 调用一次一个信息发出
     */
    public void produceMessage() {
        jmsMessagingTemplate.convertAndSend(queue, "****" + UUID.randomUUID().toString().substring(0, 6));
    }

    /**
     * 带定时投递的业务方法
     * 每3秒自动调用
     */
    //@Scheduled(fixedDelay = 3000)
    public void produceMessageScheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "** scheduled **" + UUID.randomUUID().toString().substring(0, 6));
        log.info("produceMessage send ok" );
    }
}
