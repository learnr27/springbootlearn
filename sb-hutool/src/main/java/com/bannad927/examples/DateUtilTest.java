package com.bannad927.examples;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具
 * 对于Date对象，为了便捷，使用了一个DateTime类来代替之，继承自Date对象，
 * 主要的便利在于，覆盖了toString()方法，返回yyyy-MM-dd HH:mm:ss形式的字符串，
 * 方便在输出时的调用（例如日志记录等），提供了众多便捷的方法对日期对象操作
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class DateUtilTest {

    public static void main(String[] args) {
        //1.Date、long、Calendar之间的相互转换
        //当前时间
        Date date = DateUtil.date();
        log.info("date:{}", date);
        //当前时间
        Date date1 = DateUtil.date(Calendar.getInstance());
        log.info("date1:{}", date1);
        //当前时间
        Date date2 = DateUtil.date(System.currentTimeMillis());
        log.info("date2:{}", date2);
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        log.info("now:{}", now);
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();
        log.info("today:{}", today);

        //2.字符串转日期
        /**DateUtil.parse方法会自动识别一些常用格式，包括：
         yyyy-MM-dd HH:mm:ss
         yyyy-MM-dd
         HH:mm:ss
         yyyy-MM-dd HH:mm
         yyyy-MM-dd HH:mm:ss.SSS*/
        /*String dateStr = "2020-06-11";
        Date date = DateUtil.parse(dateStr);
        log.info("date:{}", date);
        Date date1 = DateUtil.parse(dateStr, "yyyy-MM-dd");
        log.info("date1:{}", date1);*/

        //3.格式化日期输出
        /*String dateStr = "2020-06-11";
        Date date = DateUtil.parse(dateStr);
        log.info("date:{}", date);
        String format = DateUtil.format(date, "yyyy/MM/dd");
        log.info("format:{}", format);
        String formatDate = DateUtil.formatDate(date);
        log.info("formatDate:{}", formatDate);
        String formatDateTime = DateUtil.formatDateTime(date);
        log.info("formatDateTime:{}", formatDateTime);
        String formatTime = DateUtil.formatTime(date);
        log.info("formatTime:{}", formatTime);*/

        //4.获取Date对象的某个部分
        /*Date date = DateUtil.date();
        int year = DateUtil.year(date);
        log.info("year:{}", year);
        //获得月份，从0开始计数
        int month = DateUtil.month(date);
        log.info("month:{}", month);
        Month monthEnum = DateUtil.monthEnum(date);
        log.info("monthEnum:{}", monthEnum.getValue());
        int day = DateUtil.dayOfMonth(date);
        log.info("day:{}", day);
        int dayOfYear = DateUtil.dayOfYear(date);
        log.info("dayOfYear:{}", dayOfYear);
        int hour = DateUtil.hour(date,true);
        log.info("hour:{}", hour);*/

        //5.开始和结束时间
        /*String dateStr = "2020-06-11 14:00:01";
        Date date = DateUtil.parse(dateStr);
        //一天的开始 -> 2020-06-11 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);
        log.info("beginOfDay:{}", beginOfDay);
        //一天的结束 -> 2020-06-11 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);
        log.info("endOfDay:{}", endOfDay);*/

        //6.日期时间偏移
        /*String dateStr = "2020-06-11 14:00:01";
        Date date = DateUtil.parse(dateStr);
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
        log.info("newDate:{}", newDate);
        DateTime newDate2 = DateUtil.offsetDay(date, 3);
        log.info("newDate2:{}", newDate2);
        DateTime newDate3 = DateUtil.offsetHour(date, -3);
        log.info("newDate3:{}", newDate3);
        DateTime yesterday = DateUtil.yesterday();
        log.info("yesterday:{}", yesterday);
        DateTime tomorrow = DateUtil.tomorrow();
        log.info("tomorrow:{}", tomorrow);
        DateTime lastWeek = DateUtil.lastWeek();
        log.info("lastWeek:{}", lastWeek);
        DateTime nextWeek = DateUtil.nextWeek();
        log.info("nextWeek:{}", nextWeek);
        DateTime lastMonth = DateUtil.lastMonth();
        log.info("lastMonth:{}", lastMonth);
        DateTime nextMonth = DateUtil.nextMonth();
        log.info("nextMonth:{}", nextMonth);*/

        //7.日期时间差
        /*String dateStr1 = "2020-01-09 14:00:01";
        Date date1 = DateUtil.parse(dateStr1);
        String dateStr2 = "2020-06-10 14:00:01";
        Date date2 = DateUtil.parse(dateStr2);
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
        log.info("betweenDay:{}", betweenDay);
        long betweenHour = DateUtil.between(date1, date2, DateUnit.HOUR);
        log.info("betweenHour:{}", betweenHour);

        long between = DateUtil.between(DateUtil.date(), date1, DateUnit.MINUTE);
        log.info("between:{}", between);
        //Level.MINUTE表示精确到分
        String formatBetween = DateUtil.formatBetween(between, BetweenFormater.Level.MINUTE);
        log.info("formatBetween:{}", formatBetween);*/

        //8.计时器
        /*TimeInterval timer = DateUtil.timer();
        ThreadUtil.sleep(1000);
        long interval = timer.interval();
        log.info("花费毫秒数:{}", interval);
        ThreadUtil.sleep(888);
        long intervalRestart = timer.intervalRestart();
        log.info("返回花费时间，并重置开始时间:{}", intervalRestart);
        ThreadUtil.sleep(666);
        long intervalMinute = timer.intervalMinute();
        log.info("花费分钟数:{}", intervalMinute);*/

        //9.其它
        int ageOfNow = DateUtil.ageOfNow("1990-09-27");
        log.info("年龄:{}", ageOfNow);
        boolean isLeapYear = DateUtil.isLeapYear(2018);
        log.info("是否闰年:{}", isLeapYear);
        String secondToTime = DateUtil.secondToTime(123456);
        log.info("秒转时间:{}", secondToTime);
    }
}
