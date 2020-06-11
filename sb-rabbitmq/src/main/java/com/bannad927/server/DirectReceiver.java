package com.bannad927.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * 消费者从自己绑定的匿名队列中获取消息，由于该消费者可以从两个队列中获取并消费消息，
 * 可以看做两个消费者，名称分别为instance 1和instance 2
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class DirectReceiver {
    @RabbitListener(queues = "#{directQueue1.name}")
    public void receive1(String in) {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{directQueue2.name}")
    public void receive2(String in) {
        receive(in, 2);
    }

    private void receive(String in, int receiver) {
        StopWatch watch = new StopWatch();
        watch.start();
        log.info("instance {} [x] Received '{}'", receiver, in);
        doWork(in);
        watch.stop();
        log.info("instance {} [x] Done in {}s", receiver, watch.getTotalTimeSeconds());
    }

    private void doWork(String in) {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
