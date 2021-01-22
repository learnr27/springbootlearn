package com.bannad927.controller;


import com.bannad927.util.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * # volatile-lru -> remove the key with an expire set using an LRU algorithm
 * # allkeys-lru -> remove any key according to the LRU algorithm
 * # volatile-random -> remove a random key with an expire set
 * # allkeys-random -> remove a random key, any key
 * # volatile-ttl -> remove the key with the nearest expire time (minor TTL)
 * # noeviction -> don't expire at all, just return an error on write operations
 * @author: chengbinbin
 * @date: 2021.1.21
 **/
@RestController
@Slf4j
public class GoodController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${server.port}")
    private String serverPort;

    private final ReentrantLock lock = new ReentrantLock();

    public static final String REDIS_LOCK = "cbbLock";

    @Resource
    private Redisson redisson;

    /**
     * redisson
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/buyGoods")
    public String buyGoods() throws Exception {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        RLock redisLock = redisson.getLock(REDIS_LOCK);
        redisLock.lock();
        try {
            String result = redisTemplate.opsForValue().get("good:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                redisTemplate.opsForValue().set("good:001", String.valueOf(realNumber));
                // Thread.sleep(15000);
                log.info("成功买到商品，库存还剩下:{}，服务端口:{}", goodsNumber, serverPort);
                return "成功买到商品，库存还剩下:" + goodsNumber + "，服务端口:{}" + serverPort;
            } else {
                log.info("商品已经售完，，服务端口:{}", serverPort);
                return "商品已经售完";
            }
        } finally {
            if (redisLock.isLocked()&&redisLock.isHeldByCurrentThread()) {
                redisLock.unlock();
            }
        }
    }


    /**
     * redis 缓存续命
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/buyGoods1")
    public String buyGoods1() throws Exception {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        try {

          /*  Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value);
            redisTemplate.expire(REDIS_LOCK, 10L, TimeUnit.SECONDS);*/

            Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 10L, TimeUnit.SECONDS);
            if (!ifAbsent) {
                return "抢锁失败";
            }
            String result = redisTemplate.opsForValue().get("good:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                redisTemplate.opsForValue().set("good:001", String.valueOf(realNumber));
                // Thread.sleep(15000);
                log.info("成功买到商品，库存还剩下:{}，服务端口:{}", goodsNumber, serverPort);
                return "成功买到商品，库存还剩下:" + goodsNumber + "，服务端口:{}" + serverPort;
            } else {
                log.info("商品已经售完，，服务端口:{}", serverPort);
                return "商品已经售完";
            }
        } finally {
            /**
             * 1.事物
             * 2.lua脚本
             * https://redis.io/commands/set
             *
             */
            //自身事物
           /* while (true) {
                redisTemplate.watch(REDIS_LOCK);
                if (redisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)) {
                    redisTemplate.setEnableTransactionSupport(true);
                    redisTemplate.multi();
                    redisTemplate.delete(REDIS_LOCK);
                    List<Object> list = redisTemplate.exec();
                    if (null == list) {
                        continue;
                    }
                    redisTemplate.unwatch();
                    break;
                }
            }*/

            //lua脚本
            Jedis jedis = JedisUtil.getJedis();
            try {
                String script = "if redis.call('get',KEYS[1]) == ARGV[1] " +
                        "then " +
                        "    return redis.call('del',KEYS[1]) " +
                        "else " +
                        "    return 0 " +
                        "end";
                Object eval = jedis.eval(script, Collections.singletonList(REDIS_LOCK), Collections.singletonList(value));
                if ("1".equals(eval.toString())) {
                    log.info("del redis lock ok:{}", eval);
                } else {
                    log.info("del redis lock error:{}", eval);
                }
            } finally {
                if (null != jedis) {
                    jedis.close();
                }
            }
        }
    }


}





























