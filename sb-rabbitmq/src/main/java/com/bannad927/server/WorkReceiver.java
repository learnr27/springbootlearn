package com.bannad927.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * 两个消费者从队列work.hello中获取消息，名称分别为instance 1和instance 2，消息中包含.号越多，耗时越长
 * @author chengbb
 * @date 2020.6.10
 */
@RabbitListener(queues = "work.hello")
@Slf4j
public class WorkReceiver {

    private final int instance;

    public WorkReceiver(int i) {
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) {
        StopWatch watch = new StopWatch();
        watch.start();
        log.info("instance {} [x] Received '{}'", this.instance, in);
        doWork(in);
        watch.stop();
        log.info("instance {} [x] Done in {}s", this.instance, watch.getTotalTimeSeconds());
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
