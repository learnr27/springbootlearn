package com.bannad927.learn04;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void run(){
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有："+lockA+"\t尝试获得："+lockB);
            //暂停一下
            try{ TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){e.printStackTrace();}

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己持有："+lockB+"\t尝试获得："+lockA);
            }
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args){
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB").start();

        /*
        * linux  ps -ef|grep xxxx    ls -l查看当前进程的命令
        * windows下的java运行程序，也有类似ps的查看进程的命令，但是目前我们需要查看的只是java
        *           jps = java ps      jps -l
        *           jstack
        * */
    }
}
