package com.bannad927.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: chengbinbin
 * @date: 2021.1.22
 **/
public class JedisUtil {

    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        GenericObjectPoolConfig poolConfig;
        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);

    }

    public static Jedis getJedis() throws Exception {
        if (null != jedisPool) {
            return jedisPool.getResource();
        }
        throw new Exception("JedisPool is not ok");
    }

}
