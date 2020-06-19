package com.bannad927.examples;

import cn.hutool.core.date.DateUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import lombok.extern.slf4j.Slf4j;

/**
 * CronUtil通过一个全局的定时任务配置文件，实现统一的定时任务调度
 *
 * @author chengbb
 * @date 2020.6.17
 */
@Slf4j
public class CronUtilTest {

    public void TestJob() {
        //启动
        CronUtil.start();
        //如果想让执行的作业同定时任务线程同时结束，可以将定时任务设为守护线程，需要注意的是，此模式下会在调用stop时立即结束所有作业线程，请确保你的作业可以被中断：
        //使用deamon模式，
        //CronUtil.start(true);
        log.info("----------:{}", DateUtil.date());
        //关闭
        //  CronUtil.stop();
    }

    public static void main(String[] args) {
        CronUtil.schedule("*/2 * * * * *", new Task() {
            @Override
            public void execute() {
                log.info("Task excuted.");
            }
        });

        // 支持秒级别定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }

}
