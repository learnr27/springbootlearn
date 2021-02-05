package com.bannad927.learn04;

import java.util.List;

/***
 * ArrayBlockingQueue:是一个基于数组结构的有界阻塞队列，此队列按FIFO原则对元素进行排序
 * LinkedBlockingQueue:是一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量高于ArrayBlockingQueue
 * SynchronousQueue：一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移出操作，否则插入操作一直处于
 * 阻塞状态，吞吐量通常要高
 *
 *
 *
 * 2.阻塞队列
 *   2.1阻塞队列有没有好的一面
 *   2.2不得不阻塞，你如何管理
 * @author cbb
 * @date 2021.2.5
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        List list = null;
    }
}
