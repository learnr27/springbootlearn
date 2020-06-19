package com.bannad927.examples;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 农历日期
 * 农历日期，提供了生肖、天干地支、传统节日等方法。
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class ChineseDateTest {

    public static void main(String[] args) {
        ChineseDate date = new ChineseDate(DateUtil.parseDate("2017-10-09"));
        String getChineseMonth = date.getChineseMonth();
        log.info("getChineseMonth:{}", getChineseMonth);
        String getChineseMonthName = date.getChineseMonthName();
        log.info("getChineseMonthName:{}", getChineseMonthName);
        String getChineseDay = date.getChineseDay();
        log.info("getChineseDay:{}", getChineseDay);
        String getCyclical = date.getCyclical();
        log.info("getCyclical:{}", getCyclical);
        String getChineseZodiac = date.getChineseZodiac();
        log.info("getChineseZodiac:{}", getChineseZodiac);
        String getFestivals = date.getFestivals();
        log.info("getFestivals:{}", getFestivals);
        String str = date.toString();
        log.info("str:{}", str);

    }
}
