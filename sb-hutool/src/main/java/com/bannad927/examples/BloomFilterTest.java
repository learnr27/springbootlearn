package com.bannad927.examples;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import lombok.extern.slf4j.Slf4j;

/**
 * 布隆过滤器（英语：Bloom Filter）是1970年由布隆提出的。
 * 它实际上是一个很长的二进制向量和一系列随机映射函数。
 * 布隆过滤器可以用于检索一个元素是否在一个集合中。
 * 它的优点是空间效率和查询时间都远远超过一般的算法，缺点是有一定的误识别率和删除困难。
 * <p>
 * 布隆过滤器的原理是，当一个元素被加入集合时，通过K个散列函数将这个元素映射成一个位数组中的K个点，把它们置为1。
 * 检索时，我们只要看看这些点是不是都是1就（大约）知道集合中有没有它了：
 * 如果这些点有任何一个0，则被检元素一定不在；如果都是1，则被检元素很可能在。这就是布隆过滤器的基本思想。
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class BloomFilterTest {

    public static void main(String[] args) {
        BitMapBloomFilter filter = new BitMapBloomFilter(10);
        filter.add("123");
        filter.add("abc");
        filter.add("ddd");

        // 查找
        boolean contains = filter.contains("abc");
        log.info("contains:{}", contains);
    }
}
