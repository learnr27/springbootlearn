package com.bannad927.learn05;

import lombok.extern.slf4j.Slf4j;

/**
 * 分布式唯一ID生成策略之雪花算法
 * 其核心思想为，一个long型的ID：
 * <p>
 * 41 bit 作为毫秒数 - 41位的长度可以使用69年
 * 10 bit 作为机器编号 （5个bit是数据中心，5个bit的机器ID） - 10位的长度最多支持部署1024个节点
 * 12 bit 作为毫秒内序列号 - 12位的计数顺序号支持每个节点每毫秒产生4096个ID序号
 *
 * @author cbb
 * @date 2021.4.13
 */
@Slf4j
public class SnowflakeIdGenerator {

    /**
     * 统开始时间截
     */
    private final long startTime = 1618283561120L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /***
     * 数据标识id所占的位数
     */
    private final long dataCenterIdBits = 5L;

    /**
     * 持的最大机器id(十进制)，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     * -1L 左移 5位 (worker id 所占位数) 即 5位二进制所能获得的最大十进制数 - 31
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id - 31
     */
    private final long maxDataCenterId = -1 ^ (-1 << dataCenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID 左移位数 - 12 (即末 sequence 所占用的位数)
     */
    private final long workerIdMoveBits = sequenceBits;

    /**
     * 数据标识id 左移位数 - 17(12+5)
     */
    private final long dataCenterIdMoveBits = sequenceBits + workerIdBits;

    /**
     * 时间截向 左移位数 - 22(5+5+12)
     */
    private final long timestampMoveBits = sequenceBits + workerIdBits + dataCenterIdBits;

    /**
     * 生成序列的掩码(12位所对应的最大整数值)，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * @param workerId     工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    public SnowflakeIdGenerator(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker Id can't be greater than %d or less than 0", maxWorkerId));
        }

        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("DataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;

    }

    public synchronized long nextId() {
        long timestamp = currentTime();
        //如果当前时间小于上一次ID生成的时间戳: 说明系统时钟回退过 - 这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出 即 序列 > 4095
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = blockTillNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        return ((timestamp - startTime) << timestampMoveBits) | (dataCenterId << dataCenterIdMoveBits) | (workerId << workerIdMoveBits) | sequence;
    }

    /**
     * 塞到下一个毫秒 即 直到获得新的时间戳
     *
     * @param lastTimestamp
     * @return
     */
    protected long blockTillNextMillis(long lastTimestamp) {
        long timestamp = currentTime();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTime();

        }
        return timestamp;
    }

    /**
     * 阻塞到下一个毫秒 即 直到获得新的时间戳
     *
     * @return
     */
    protected long currentTime() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {
        SnowflakeIdGenerator idWorker = new SnowflakeIdGenerator(0, 0);
        for (int i = 0; i < 100; i++) {
            long id = idWorker.nextId();
            log.info("id:{}", id);
        }
    }
}
