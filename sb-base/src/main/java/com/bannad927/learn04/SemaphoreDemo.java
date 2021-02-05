package com.bannad927.learn04;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量主要用于两个目的，一个是多个共享资源的互斥使用，一个是并发线程数的控制。
 * 争车位
 *
 * @author cbb
 * @date 2021.2.5
 */
@Slf4j
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    log.info("{}:抢到车位", Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(3);
                    log.info("{}:停车3秒后离开车位", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
