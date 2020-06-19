package com.bannad927.examples;

import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.io.watch.watchers.DelayWatcher;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 文件监听
 * 支持多级目录的监听（WatchService只支持一级目录），可自定义监听目录深度
 * 延迟合并触发支持（文件变动时可能触发多次modify，支持在某个时间范围内的多次修改事件合并为一个修改事件）
 * 简洁易懂的API方法，一个方法即可搞定监听，无需理解复杂的监听注册机制。
 * 多观察者实现，可以根据业务实现多个Watcher来响应同一个事件（通过WatcherChain）
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class WatchMonitorTest {

    public static void main(String[] args) {
        //1.监听指定事件
        /**WatchMonitor提供的事件有：
         ENTRY_MODIFY 文件修改的事件
         ENTRY_CREATE 文件或目录创建的事件
         ENTRY_DELETE 文件或目录删除的事件
         OVERFLOW 丢失的事件*/
        /*File file = FileUtil.file("d:/test");
        WatchMonitor watchMonitor = WatchMonitor.create(file, WatchMonitor.ENTRY_MODIFY);
        watchMonitor.setWatcher(new Watcher() {
            @Override
            public void onCreate(WatchEvent<?> watchEvent, Path path) {
                Object obj = watchEvent.context();
                log.info("创建：{}-> {}", path, obj);
            }

            @Override
            public void onModify(WatchEvent<?> watchEvent, Path path) {
                Object obj = watchEvent.context();
                log.info("修改：{}-> {}", path, obj);
            }

            @Override
            public void onDelete(WatchEvent<?> watchEvent, Path path) {
                Object obj = watchEvent.context();
                log.info("删除：{}-> {}", path, obj);
            }

            @Override
            public void onOverflow(WatchEvent<?> watchEvent, Path path) {
                Object obj = watchEvent.context();
                log.info("Overflow：{}-> {}", path, obj);
            }
        });

        //设置监听目录的最大深入，目录层级大于制定层级的变更将不被监听，默认只监听当前层级目录
        watchMonitor.setMaxDepth(3);
        //启动监听
        watchMonitor.start();*/

        //2.监听全部事件

       /* WatchMonitor.createAll("d:/test", new SimpleWatcher() {
            @Override
            public void onCreate(WatchEvent<?> watchEvent, Path path) {
                Object obj = watchEvent.context();
                log.info("创建：{}-> {}", path, obj);
            }

            @Override
            public void onModify(WatchEvent<?> watchEvent, Path path) {
                Object obj = watchEvent.context();
                log.info("修改：{}-> {}", path, obj);
            }

            @Override
            public void onDelete(WatchEvent<?> watchEvent, Path path) {
                Object obj = watchEvent.context();
                log.info("删除：{}-> {}", path, obj);
            }

            @Override
            public void onOverflow(WatchEvent<?> watchEvent, Path path) {
                Object obj = watchEvent.context();
                log.info("Overflow：{}-> {}", path, obj);
            }
        }).start();
    */

        //3.延迟处理监听事件
        /**
         * 在监听目录或文件时，如果这个文件有修改操作，JDK会多次触发modify方法，为了解决这个问题，我们定义了DelayWatcher，
         * 此类通过维护一个Set将短时间内相同文件多次modify的事件合并处理触发，从而避免以上问题
         */
        WatchMonitor monitor = WatchMonitor.createAll("d:/", new DelayWatcher(new Watcher() {

            @Override
            public void onCreate(WatchEvent<?> watchEvent, Path path) {

            }

            @Override
            public void onModify(WatchEvent<?> watchEvent, Path path) {

            }

            @Override
            public void onDelete(WatchEvent<?> watchEvent, Path path) {

            }

            @Override
            public void onOverflow(WatchEvent<?> watchEvent, Path path) {

            }
        }, 500));
        monitor.start();
    }

}