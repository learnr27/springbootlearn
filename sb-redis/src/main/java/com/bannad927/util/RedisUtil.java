package com.bannad927.util;

import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: chengbinbin
 * @date: 2021.1.18
 **/
@Component(value = "redisUtil")
public class RedisUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 设置key
     *
     * @param key
     * @param value
     */
    public void setKey(String key, String value) {
        stringRedisTemplate.delete(key);
        this.stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getKey(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置键值对并设置过期时间
     *
     * @param key    键
     * @param value  值
     * @param second 过期时间(单位秒)
     */
    public void setKey(String key, String value, long second) {
        stringRedisTemplate.delete(key);
        this.stringRedisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<String> keys = stringRedisTemplate.keys(pattern);
        if (keys.size() > 0) {
            stringRedisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return stringRedisTemplate.hasKey(key);
    }


    /**
     * 左压出栈
     *
     * @param key
     * @return
     */
    public String listLeftPop(final String key) {
        return this.stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 向右压栈
     *
     * @param key
     * @param value
     */
    public void listRightPush(String key, String value) {
        this.stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 返回链表大小
     *
     * @param key
     */
    public Long listSize(String key) {
        return this.stringRedisTemplate.opsForList().size(key);
    }


    // ================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return stringRedisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            stringRedisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            stringRedisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            stringRedisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            stringRedisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        stringRedisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return stringRedisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return stringRedisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return stringRedisTemplate.opsForHash().increment(key, item, -by);
    }


    /**
     * redis 读写测试
     */
    public void testSpringRedis() {

        try {
            // ApplicationContext context = new
            // ClassPathXmlApplicationContext("spring-redis.xml");
            // StringRedisTemplate stringRedisTemplate =
            // context.getBean("stringRedisTemplate",
            // StringRedisTemplate.class);
            // String读写
            stringRedisTemplate.delete("myStr");
            stringRedisTemplate.opsForValue().set("myStr", "http://yjmyzz.cnblogs.com/");
            System.out.println(stringRedisTemplate.opsForValue().get("myStr"));
            System.out.println("---------------");

            // List读写
            stringRedisTemplate.delete("myList");
            stringRedisTemplate.opsForList().rightPush("myList", "A");
            stringRedisTemplate.opsForList().rightPush("myList", "B");
            stringRedisTemplate.opsForList().leftPush("myList", "0");
            List<String> listCache = stringRedisTemplate.opsForList().range("myList", 0, -1);
            for (String s : listCache) {
                System.out.println(s);
            }
            System.out.println("---------------");

            // Set读写
            stringRedisTemplate.delete("mySet");
            stringRedisTemplate.opsForSet().add("mySet", "A");
            stringRedisTemplate.opsForSet().add("mySet", "B");
            stringRedisTemplate.opsForSet().add("mySet", "C");
            Set<String> setCache = stringRedisTemplate.opsForSet().members("mySet");
            for (String s : setCache) {
                System.out.println(s);
            }
            System.out.println("---------------");

            // Hash读写
            stringRedisTemplate.delete("myHash");
            stringRedisTemplate.opsForHash().put("myHash", "PEK", "北京");
            stringRedisTemplate.opsForHash().put("myHash", "SHA", "上海虹桥");
            stringRedisTemplate.opsForHash().put("myHash", "PVG", "浦东");
            Map<Object, Object> hashCache = stringRedisTemplate.opsForHash().entries("myHash");
            for (Map.Entry<Object, Object> entry : hashCache.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            System.out.println("---------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * redis 得到所有的master and slave 信息
     */
    public void testGetAllMasterAndSlave() {

        RedisSentinelConnection conn = stringRedisTemplate.getConnectionFactory().getSentinelConnection();

        for (RedisServer master : conn.masters()) {
            System.out.println("master => " + master);// 打印master信息
            Collection<RedisServer> slaves = conn.slaves(master);
            // 打印该master下的所有slave信息
            for (RedisServer slave : slaves) {
                System.out.println("slaves of " + master + " => " + slave);
            }
            System.out.println("--------------");
        }
    }

    /*
     * 测试redis 缓存object 和 list 类型数据(方案：用json将object和list序列化)
     */
    public void testRedisCacheObjectAndList() {

//        User user1 = new User("zhangsan", "123456", "222888@qq.com", "15824812342", 'M', 22);
//      // fastJson 序列化
//      String jsonStr = JSONObject.toJSONString(user1);
//      System.out.println(">>>>>>>>>>>>>>>>" + jsonStr);
//      // fastJson 反序列化
//      user1 = JSONObject.parseObject(jsonStr, User.class);
//      System.out.println(">>>>>>>>>>>>>>>>>>" + user1);
//
//        stringRedisTemplate.delete("user1");
//        // 将object 用 json 序列化后保存redis
//        stringRedisTemplate.opsForValue().set("user1", JSONObject.toJSONString(user1));
//
//        user1 = JSONObject.parseObject(stringRedisTemplate.opsForValue().get("user1"), User.class);
//        System.out.println("-----------------------" + user1);
    }

    /**
     * 上锁
     * 将键值对设定一个指定的时间timeout.
     *
     * @param key
     * @param timeout 键值对缓存的时间，单位是秒
     * @return 设置成功返回true，否则返回false
     */
    public boolean tryLock(String key, String value, long timeout) {
        //底层原理就是Redis的setnx方法
        boolean isSuccess = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        if (isSuccess) {
            //设置分布式锁的过期时间
            stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
        return isSuccess;
    }

}
