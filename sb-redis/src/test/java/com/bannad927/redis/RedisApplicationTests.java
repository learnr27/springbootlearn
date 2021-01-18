package com.bannad927.redis;

import com.bannad927.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RedisApplicationTests {

    @Resource
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        redisUtil.setKey("orderNum:123456","orderNum:123456",10);
        redisUtil.setKey("orderNum:1234567","orderNum:1234567",22);
        redisUtil.setKey("orderNum:12345678","orderNum:12345678",33);
    }

}
