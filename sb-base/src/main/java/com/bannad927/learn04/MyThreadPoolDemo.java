package com.bannad927.learn04;

import java.util.concurrent.*;

/**
 * @author cbb
 * @date 2021.2.5
 */
public class MyThreadPoolDemo {

    /**
     * Array Arrays  辅助工具类
     * Collection Collections
     * Executor Executors
     *
     * @param args
     */
    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        //AbortPolicy（默认）：直接抛出RejectedException。
        //CallerRunsPolicy：不会抛弃任务也不会抛出异常，而是回退给调用者
        //DiscardOldestPolicy：丢弃等待最久的任务。
        //DiscardPolicy：直接丢弃任务，不做任何处理。
        try {
            for (int i = 0; i < 11; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() );
                  /*  try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    private static void threadPoolExecutorIni() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 0; i < 10; i++) {
                final int tempInt = i;
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务"+tempInt);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    private static void threadPoolExecutorDemo() {
        //System.out.println(Runtime.getRuntime().availableProcessors());

        //一池5个处理线程  new LinkedBlockingQueue<Runnable>()  一池固定数量的线程。执行长期任务，性能会很好
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一池1个线程 new LinkedBlockingQueue<Runnable>() 一池一个线程。一个任务一个线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //一池N个线程 new SynchronousQueue<Runnable>() 一池不定数量的线程。适用于短期异步小任务或者负载较轻的任务。
        // ExecutorService threadPool = Executors.newCachedThreadPool();
        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");

                  /*  try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                });
            }
        } catch (Exception e) {

        } finally {
            threadPool.shutdown();

        }
    }
}
