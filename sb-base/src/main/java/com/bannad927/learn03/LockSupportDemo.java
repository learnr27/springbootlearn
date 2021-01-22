package com.bannad927.learn03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程等待唤醒机制
 *
 * @author: chengbinbin
 * @date: 2021.1.20
 **/
@Slf4j
public class LockSupportDemo {

    public static Object objectLock = new Object();

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {


    }

    private static void synchronizeAwaitSignal() {
        new Thread(() -> {
            lock.lock();
            try {
                log.info("获得:{}", Thread.currentThread().getName());
                condition.await();
                log.info("被唤醒:{}", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                log.info("通知:{}", Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }

    public static void synchronizeWaitNotify() {
        new Thread(() -> {
            synchronized (objectLock) {
                log.info("获得:{}");
                try {
                    objectLock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("被唤醒:{}");
            }
        }, "A").start();
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                log.info("通知:{}");
            }
        }, "B").start();
    }
}



































