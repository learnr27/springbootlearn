package com.bannad927.examples.controller;

import cn.hutool.core.date.DateUtil;
import com.bannad927.examples.IdGeneratorSnowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chengbb
 * @date 2020.7.10
 */
@RestController
@Slf4j
public class IndexController {

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @GetMapping("index")
    public String index() {
        try {
            ExecutorService threadPool = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 20; i++) {
                threadPool.submit(() -> {
                    log.info("IdGeneratorSnowflake:{}", idGeneratorSnowflake.snowflakeId());
                });
            }
            threadPool.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return DateUtil.now();
    }
}
