package com.bannad927.learn04;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多个线程同时读一个资源类没有问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是写共享资源只能有一个线程。
 * <p>
 * 写操作：原子+独占，整个过程必须是一个完整的统一体，中间不许被分割，被打断。
 * <p>
 * 读-读 能共存
 * 读-写 不能共存
 * 写-些 不能共存
 *
 * @author cbb
 * @date 2021.2.4
 */
@Slf4j
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");

            }, String.valueOf(i)).start();
        }
    }
}

@Slf4j
class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();
    //private Lock lock = new ReentrantLock();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            log.info("正在写入:{}", key);
            TimeUnit.MILLISECONDS.sleep(100);
            map.put(key, value);
            log.info("写入完成:{}", key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();

        }

    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            log.info("正在读取：{}", key);
            TimeUnit.MILLISECONDS.sleep(300);
            Object result = map.get(key);
            log.info("读取完成:{}", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();

        }
    }

    public void clear() {
        map.clear();
    }
}