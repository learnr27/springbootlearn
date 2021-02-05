package com.bannad927.learn04;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 让一些线程阻塞直到另一些线程完成操作后才被唤醒
 * 主要有两个方法，await方法会被阻塞。countDown会让计数器-1，不会阻塞。将计数器变为0时，调用await方法的线程会被唤醒，继续执行。
 *
 * @author cbb
 * @date 2021.2.5
 */
@Slf4j
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                log.info("{}:国，被灭", Thread.currentThread().getName());
                countDownLatch.countDown();
            }, CountTryEnum.forEachCountTryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        log.info("{}:统一六国", Thread.currentThread().getName());
        log.info("TWO:{}", CountTryEnum.TWO);
        log.info("TWO getRetMessage:{}", CountTryEnum.TWO.getRetMessage());
        log.info("TWO getRetCode:{}", CountTryEnum.TWO.getRetCode());
    }

    private static void demo1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                log.info("{}:上完自习，离开教室", Thread.currentThread().getName());
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        log.info("{}:班长最后关门走人", Thread.currentThread().getName());
    }
}
