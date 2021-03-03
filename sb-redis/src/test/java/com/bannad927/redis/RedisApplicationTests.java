package com.bannad927.redis;

import com.bannad927.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class RedisApplicationTests {

    @Resource
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        redisUtil.sendMessage();
       /* redisUtil.setBit("userId:1", 1, true);
        redisUtil.setBit("userId:1", 2, false);
        redisUtil.setBit("userId:1", 3, true);
        log.info("1:{}", redisUtil.getBit("userId:1",1));//true
        log.info("2:{}", redisUtil.getBit("userId:1",2));//false
        log.info("4:{}", redisUtil.getBit("userId:1",4));//false
        log.info("bitCount:{}", redisUtil.bitCount("userId:1"));//2*/

    }

}
