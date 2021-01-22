package com.bannad927.learn03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重复入锁
 * @author: chengbinbin
 * @date: 2021.1.20
 **/
@Slf4j
public class ReEnterLockDemo {

    static Object objectLockA = new Object();

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {


    }


    public static void lockTest() {
        new Thread(() -> {
            lock.lock();
            try {
                log.info("外层:{}");
                lock.lock();
                try {
                    log.info("内层:{}");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();
    }

    private static synchronized void functionA() {
        log.info("外:{}");
        functionB();
    }

    private static synchronized void functionB() {
        log.info("中:{}");
        functionC();
    }

    private static synchronized void functionC() {
        log.info("内:{}");
    }


    public static void m1() {
        new Thread(() -> {
            synchronized (objectLockA) {
                log.info("外层调用:{}");
                synchronized (objectLockA) {
                    log.info("中层调用:{}");
                    synchronized (objectLockA) {
                        log.info("内存调用:{}");
                    }
                }
            }
        }, "t1").start();
    }

}
