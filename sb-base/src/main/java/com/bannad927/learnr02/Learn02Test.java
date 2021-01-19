package com.bannad927.learnr02;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

/**
 * BlockingQueue
 *
 * @author: chengbinbin
 * @date: 2021.1.19
 **/
@Slf4j
public class Learn02Test {


    /**
     * ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
     * LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。
     * PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
     * DelayQueue：一个使用优先级队列实现的无界阻塞队列。
     * SynchronousQueue：一个不存储元素的阻塞队列。
     * LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
     * LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
     */

    /**
     * ArrayBlockingQueue：
     * 一个对象数组+一把锁+两个条件
     * 入队与出队都用同一把锁
     * 在只有入队高并发或出队高并发的情况下，因为操作数组，且不需要扩容，性能很高
     * 采用了数组，必须指定大小，即容量有限
     * <p>
     * LinkedBlockingQueue：
     * 一个单向链表+两把锁+两个条件
     * 两把锁，一把用于入队，一把用于出队，有效的避免了入队与出队时使用一把锁带来的竞争。
     * 在入队与出队都高并发的情况下，性能比ArrayBlockingQueue高很多
     * 采用了链表，最大容量为整数最大值，可看做容量无限
     */
    private static BlockingQueue<DownloadCenter> task = new LinkedBlockingQueue<DownloadCenter>(5);

    public static void main(String[] args) throws InterruptedException {
        consumeQueue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                produceQueue();
            }
        }).start();
    }


    /**
     * 入队
     * offer(E e)：如果队列没满，立即返回true； 如果队列满了，立即返回false-->不阻塞
     * put(E e)：如果队列满了，一直阻塞，直到队列不满了或者线程被中断-->阻塞
     * offer(E e, long timeout, TimeUnit unit)：在队尾插入一个元素,，如果队列已满，则进入等待，直到出现以下三种情况：
     * -->阻塞被唤醒
     * -->等待时间超时
     * -->当前线程被中断
     */
    public static void produceQueue() {
        try {
            for (int i = 0; i < 10; i++) {
                DownloadCenter downloadCenter = new DownloadCenter();
                downloadCenter.setDownloadContent("下载内容:" + i);
                downloadCenter.setTypesOf(i % 2);
                downloadCenter.setDownloadParameters("下载参数:" + i);
                task.put(downloadCenter);
                log.info("produceQueue:{}", i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 出队
     * poll()：如果没有元素，直接返回null；如果有元素，出队
     * take()：如果队列空了，一直阻塞，直到队列不为空或者线程被中断-->阻塞
     * poll(long timeout, TimeUnit unit)：如果队列不空，出队；如果队列已空且已经超时，返回null；如果队列已空且时间未超时，则进入等待，直到出现以下三种情况：
     * -->被唤醒
     * -->等待时间超时
     * -->当前线程被中断
     */
    public static void consumeQueue() {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("download-Center-task").build();
        ExecutorService pool = new ThreadPoolExecutor(5, 10, 3650L,
                TimeUnit.DAYS, new LinkedBlockingQueue<>(1024), factory, new ThreadPoolExecutor.AbortPolicy());
        pool.execute(() -> {
            while (true) {
                try {
                    DownloadCenter downloadCenter = task.take();
                    log.info("consumeQueue:{}", downloadCenter.getDownloadContent());
                    Thread.sleep(new Random().nextInt(1000));
                    // todo 业务逻辑操作
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        pool.shutdown();
    }


}
