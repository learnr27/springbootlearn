package com.bannad927.learn03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 抽象排队同步器
 * @author: chengbinbin
 * @date: 2021.1.21
 **/
@Slf4j
public class AQSDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();


           new Thread(()->{
               lock.lock();
               try {
                   log.info("{}:获取", Thread.currentThread().getName());
                  Thread.sleep(20000);
               } catch (Exception e) {
                   e.printStackTrace();
               }finally {
                   lock.unlock();
               }
           },"a").start();

        new Thread(()->{
            lock.lock();
            try {
                log.info("{}:获取", Thread.currentThread().getName());
                Thread.sleep(20000);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"b").start();

        new Thread(()->{
            lock.lock();
            try {
                log.info("{}:获取", Thread.currentThread().getName());
                Thread.sleep(20000);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"c").start();


    }
}














































































