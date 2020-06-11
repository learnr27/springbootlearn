package com.bannad927.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生产者通过send方法向交换机exchange.fanout中发送消息，消息中包含一定数量的.号；
 *
 * @author chengbb
 * @date 2020.6.10
 */
@Slf4j
public class FanoutSender {
    @Autowired
    private RabbitTemplate template;

    private static final String exchangeName = "exchange.fanout";

    /**
     * 添加发布/订阅模式相关Java配置，创建一个名为exchange.fanout的交换机、一个生产者、两个消费者和两个匿名队列，将两个匿名队列都绑定到交换机；
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
        template.convertAndSend(exchangeName, "", message);
        log.info(" [x] Sent '{}'", message);
    }

}
