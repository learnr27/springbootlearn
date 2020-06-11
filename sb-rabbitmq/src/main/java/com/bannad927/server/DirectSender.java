package com.bannad927.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生产者通过send方法向交换机exchange.direct中发送消息，
 * 发送时使用不同的路由键，根据路由键会被转发到不同的队列；
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class DirectSender {

    @Autowired
    private RabbitTemplate template;

    private static final String exchangeName = "exchange.direct";

    private final String[] keys = {"orange", "black", "green"};

    /**
     * 添加路由模式相关Java配置，创建一个名为exchange.direct的交换机、一个生产者、两个消费者和两个匿名队列，
     * 队列通过路由键都绑定到交换机，队列1的路由键为orange和black，队列2的路由键为green和black；
     *
     * @param index
     */
    public void send(int index) {
        StringBuilder builder = new StringBuilder("Hello to ");
        int limitIndex = index % 3;
        String key = keys[limitIndex];
        builder.append(key).append(' ');
        builder.append(index + 1);
        String message = builder.toString();
        template.convertAndSend(exchangeName, key, message);
        log.info(" [x] Sent '{}'", message);
    }
}
