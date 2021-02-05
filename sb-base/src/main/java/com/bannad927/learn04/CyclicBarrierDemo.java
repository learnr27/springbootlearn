package com.bannad927.learn04;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 人到期才能开会
 * <p>
 * CyclicBarrie字面上就是可循环使用的屏障。当一组线程得到一个屏障（同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会打开，所有被屏障拦截的线程才会继续工作。进入屏障通过await方法。
 *
 * @author cbb
 * @date 2021.2.5
 */
@Slf4j
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            log.info("召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                log.info("{}：收集到第：{}颗龙珠", Thread.currentThread().getName(), tempInt);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
