package com.bannad927.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生产者通过send方法向队列work.hello中发送消息，消息中包含一定数量的.号；
 *
 * @author chengbb
 * @date 2020.6.10
 */
@Slf4j
public class WorkSender {

    @Autowired
    private RabbitTemplate template;

    private static final String queueName = "work.hello";

    /**
     * 添加工作模式相关Java配置，创建一个名为work.hello的队列、一个生产者和两个消费者；
     *
     * @param index
     */
    public void send(int index) {
        StringBuilder builder = new StringBuilder("Hello");
        int limitIndex = index % 3 + 1;
        for (int i = 0; i < limitIndex; i++) {
            builder.append('.');
        }
        builder.append(index + 1);
        String message = builder.toString();
        template.convertAndSend(queueName, message);
        log.info(" [x] Sent '{}'", message);
    }

}
