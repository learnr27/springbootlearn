package com.bannad927.examples;

import cn.hutool.cache.file.LFUFileCache;
import lombok.extern.slf4j.Slf4j;

/**
 * FIFOCache
 * FIFO(first in first out) 先进先出策略。元素不停的加入缓存直到缓存满为止，当缓存满时，清理过期缓存对象，清理后依旧满则删除先入的缓存（链表首部对象）。
 * <p>
 * 优点：简单快速 缺点：不灵活，不能保证最常用的对象总是被保留
 * <p>
 * LFUCache
 * LFU(least frequently used) 最少使用率策略。根据使用次数来判定对象是否被持续缓存（使用率是通过访问次数计算），当缓存满时清理过期对象，清理后依旧满的情况下清除最少访问（访问计数最小）的对象并将其他对象的访问数减去这个最小访问数，以便新对象进入后可以公平计数。
 * <p>
 * LRUCache
 * LRU (least recently used)最近最久未使用缓存。根据使用时间来判定对象是否被持续缓存，当对象被访问时放入缓存，当缓存满了，最久未被使用的对象将被移除。此缓存基于LinkedHashMap，因此当被缓存的对象每被访问一次，这个对象的key就到链表头部。这个算法简单并且非常快，他比FIFO有一个显著优势是经常使用的对象不太可能被移除缓存。缺点是当缓存满时，不能被很快的访问。
 * <p>
 * TimedCache
 * 定时缓存，对被缓存的对象定义一个过期时间，当对象超过过期时间会被清理。此缓存没有容量限制，对象只有在过期后才会被移除
 * <p>
 * WeakCache
 * 弱引用缓存。对于一个给定的键，其映射的存在并不阻止垃圾回收器对该键的丢弃，这就使该键成为可终止的，被终止，然后被回收。丢弃某个键时，其条目从映射中有效地移除。该类使用了WeakHashMap做为其实现，缓存的清理依赖于JVM的垃圾回收。
 * <p>
 * FileCach
 * FileCach是一个独立的缓存，主要是将小文件以byte[]的形式缓存到内容中，减少文件的访问，以解决频繁读取文件引起的性能问题。
 * <p>
 * 主要实现有：
 * <p>
 * LFUFileCache
 * LRUFileCache
 *
 * @author chengbb
 * @date 2020.6.18
 */
@Slf4j
public class CacheTest {

    public static void main(String[] args) {
        //1.先入先出-FIFOCache
        /**
         FIFO(first in first out) 先进先出策略。元素不停的加入缓存直到缓存满为止，当缓存满时，清理过期缓存对象，清理后依旧满则删除先入的缓存（链表首部对象）。
         优点：简单快速 缺点：不灵活，不能保证最常用的对象总是被保留
         */
        /*Cache<String, String> fifo = CacheUtil.newFIFOCache(3);
        //加入元素，每个元素可以设置其过期时长，DateUnit.SECOND.getMillis()代表每秒对应的毫秒数，在此为3秒
        fifo.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        fifo.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        fifo.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        //由于缓存容量只有3，当加入第四个元素的时候，根据FIFO规则，最先放入的对象将被移除
        fifo.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);
        log.info(":{}", fifo.get("key1"));*/

        //2.最少使用-LFUCache
        /**
         LFU(least frequently used) 最少使用率策略。根据使用次数来判定对象是否被持续缓存（使用率是通过访问次数计算），
         当缓存满时清理过期对象，清理后依旧满的情况下清除最少访问（访问计数最小）的对象并将其他对象的访问数减去这个最小访问数，以便新对象进入后可以公平计数。
         */
        /*Cache<String, String> lfuCache = CacheUtil.newLFUCache(3);
        //通过实例化对象创建
        //LFUCache<String, String> lfuCache = new LFUCache<String, String>(3);
        lfuCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        lfuCache.get("key1");
        lfuCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        lfuCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        lfuCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);
        //由于缓存容量只有3，当加入第四个元素的时候，根据LRU规则，最少使用的将被移除（2,3被移除）
        log.info(":{}", lfuCache.get("key2"));
        log.info(":{}", lfuCache.get("key3"));*/

        //3.最少使用-LFUCache
        /**
         LRU (least recently used)最近最久未使用缓存。根据使用时间来判定对象是否被持续缓存，当对象被访问时放入缓存，当缓存满了，最久未被使用的对象将被移除。
         此缓存基于LinkedHashMap，因此当被缓存的对象每被访问一次，这个对象的key就到链表头部。
         这个算法简单并且非常快，他比FIFO有一个显著优势是经常使用的对象不太可能被移除缓存。缺点是当缓存满时，不能被很快的访问。
         */
        /*Cache<String, String> lruCache = CacheUtil.newLRUCache(3);
        //通过实例化对象创建
        //LRUCache<String, String> lruCache = new LRUCache<String, String>(3);
        lruCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        lruCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        lruCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        lruCache.get("key1");
        lruCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);
        //由于缓存容量只有3，当加入第四个元素的时候，根据LRU规则，最少使用的将被移除（2被移除）
        log.info(":{}", lruCache.get("key2"));*/

        //4.超时-TimedCache
        /**
         定时缓存，对被缓存的对象定义一个过期时间，当对象超过过期时间会被清理。此缓存没有容量限制，对象只有在过期后才会被移除。
         */
        //创建缓存，默认4毫秒过期
        /*TimedCache<String, String> timedCache = CacheUtil.newTimedCache(4);
        //实例化创建
        //TimedCache<String, String> timedCache = new TimedCache<String, String>(4);
        timedCache.put("key1", "value1", 1);
        timedCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 5);
        //默认过期(4毫秒)
        timedCache.put("key3", "value3");
        //启动定时任务，每5毫秒秒检查一次过期
        timedCache.schedulePrune(5);
        //等待5毫秒
        ThreadUtil.sleep(5);
        //5毫秒后由于value2设置了5毫秒过期，因此只有value2被保留下来
        log.info("key1:{}", timedCache.get("key1"));
        log.info("key2:{}", timedCache.get("key2"));
        //5毫秒后，由于设置了默认过期，key3只被保留4毫秒，因此为null
        log.info("key3:{}", timedCache.get("key3"));
        //取消定时清理
        timedCache.cancelPruneSchedule();*/

        //5.弱引用-WeakCache
        /**
         弱引用缓存。对于一个给定的键，其映射的存在并不阻止垃圾回收器对该键的丢弃，这就使该键成为可终止的，被终止，然后被回收。
         丢弃某个键时，其条目从映射中有效地移除。
         该类使用了WeakHashMap做为其实现，缓存的清理依赖于JVM的垃圾回收。
         */
        //WeakCache<String, String> weakCache = CacheUtil.newWeakCache(DateUnit.SECOND.getMillis() * 3);

        //6.文件缓存-FileCache
        /**
         FileCache主要是将小文件以byte[]的形式缓存到内存中，减少文件的访问，以解决频繁读取文件引起的性能问题。
         */
        //参数1：容量，能容纳的byte数
        //参数2：最大文件大小，byte数，决定能缓存至少多少文件，大于这个值不被缓存直接读取
        //参数3：超时。毫秒
        LFUFileCache cache = new LFUFileCache(1000, 500, 2000);
        byte[] bytes = cache.getFileBytes("D:/test1.txt");

    }
}
