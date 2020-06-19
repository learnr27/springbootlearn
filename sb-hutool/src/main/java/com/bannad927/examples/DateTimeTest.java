package com.bannad927.examples;

import cn.hutool.core.date.DateTime;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 日期时间对象
 * DateTime类继承于java.util.Date类，为Date类扩展了众多简便方法，
 * 这些方法多是DateUtil静态方法的对象表现形式，使用DateTime对象可以完全替代开发中Date对象的使用
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class DateTimeTest {

    public static void main(String[] args) {
        //1.新建对象
        /**DateTime对象包含众多的构造方法，构造方法支持的参数有：
         Date
         Calendar
         String(日期字符串，第二个参数是日期格式)
         long 毫秒数*/
       Date date = new Date();
        DateTime time = new DateTime(date);
        log.info("time:{}", time);
        DateTime now = DateTime.now();
        log.info("now:{}", now);
        DateTime dt = DateTime.of(date);
        log.info("dt:{}", dt);

        //2.使用对象
        /*DateTime dateTime = new DateTime("2020-06-11 14:28:40", DatePattern.NORM_DATETIME_FORMAT);
        int year = dateTime.year();
        log.info("year:{}", year);
        Week week = dateTime.dayOfWeekEnum();
        log.info("week.toChinese:{}  \t week.getValue:{} \t week:{}", week.toChinese(), week.getValue(), week);
        Month month = dateTime.monthEnum();
        log.info("month:{}", month);
        int day = dateTime.dayOfMonth();
        log.info("day:{}", day);*/

        //3.对象的可变性
        /*DateTime dateTime = new DateTime("2020-06-11 14:28:40", DatePattern.NORM_DATETIME_FORMAT);
        //默认情况下DateTime为可变对象，此时offset == dateTime
        DateTime offset = dateTime.offset(DateField.YEAR, 1);
        log.info("offset:{}", offset);
        //设置为不可变对象后变动将返回新对象，此时offset != dateTime
        dateTime.setMutable(false);
        offset = dateTime.offset(DateField.YEAR, 1);
        log.info("offset:{}", offset);*/

        //4.格式化为字符串
        /*DateTime dateTime = new DateTime("2020-06-11 14:28:40", DatePattern.NORM_DATETIME_FORMAT);
        String dateStr = dateTime.toString();
        log.info("dateStr:{}", dateStr);*/
    }
}
