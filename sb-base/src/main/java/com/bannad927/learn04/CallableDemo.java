package com.bannad927.learn04;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author cbb
 * @date 2021.2.5
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable callable;
        FutureTask futureTask = new FutureTask<>(new MyThread());

        Thread thread = new Thread(futureTask, "AA");
        new Thread(futureTask, "BB").start();
        thread.start();
        System.out.println("+++++++++++++++" + Thread.currentThread().getName());
        // int r2 = (int) futureTask.get();
        int r1 = 100;


        //要求获得Callable线程的计算结果，如果没有计算完成就要强求，会导致阻塞，知道计算完成，建议放在最后
        int r2 = (int) futureTask.get();
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r2 + r1);
    }

}

class MyThread implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(".........come in callable"+Thread.currentThread().getName());
        return 1024;
    }
}
