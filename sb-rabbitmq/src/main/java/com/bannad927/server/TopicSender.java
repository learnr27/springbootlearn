package com.bannad927.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生产者通过send方法向交换机exchange.topic中发送消息，消息中包含不同的路由键
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class TopicSender {

    @Autowired
    private RabbitTemplate template;

    private static final String exchangeName = "exchange.topic";

    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    /**
     * 添加通配符模式相关Java配置，创建一个名为exchange.topic的交换机、一个生产者、两个消费者和两个匿名队列，
     * 匹配*.orange.*和*.*.rabbit发送到队列1，匹配lazy.#发送到队列2；
     *
     * @param index
     */
    public void send(int index) {
        StringBuilder builder = new StringBuilder("Hello to ");
        int limitIndex = index % keys.length;
        String key = keys[limitIndex];
        builder.append(key).append(' ');
        builder.append(index + 1);
        String message = builder.toString();
        template.convertAndSend(exchangeName, key, message);
        log.info(" [x] Sent '{}'", message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
