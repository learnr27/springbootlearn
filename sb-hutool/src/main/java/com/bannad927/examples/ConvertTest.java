package com.bannad927.examples;

import cn.hutool.core.convert.Convert;
import lombok.extern.slf4j.Slf4j;

/**
 * 类型转换工具类-Convert
 * <p>
 * Convert类可以说是一个工具方法类，里面封装了针对Java常见类型的转换，用于简化类型转换。
 * Convert类中大部分方法为toXXX，参数为Object，可以实现将任意可能的类型转换为指定类型。同时支持第二个参数defaultValue用于在转换失败时返回一个默认值。
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class ConvertTest {


    public static void main(String[] args) {
        // 1.转换为字符串：
      /*  int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);

        long[] b = {1, 2, 3, 4, 5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        log.info("String: {}", bStr);*/

        //2.转换为指定类型数组：
       /* String[] b = { "1", "2", "3", "4" };
        //结果为Integer数组
        Integer[] intArray = Convert.toIntArray(b);

        long[] c = {1,2,3,4,5};
        //结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);*/

        //3.转换为日期对象：
        /*String a = "2020-06-11";
        Date value = Convert.toDate(a,new Date());*/

        //4.转换为集合
       /* Object[] a = {"a", "你", "好", "", 1};
        List<?> list = Convert.convert(List.class, a);
        List<?> list1 = Convert.toList(a);
        log.info("list: {}", list1);*/

        //5.半角转全角
        /*String a = "123456789";
        String sbc = Convert.toSBC(a);
        log.info("sbc: {}", sbc);*/

        //6.全角转半角
        /*String a = "１２３４５６７８９";
        String dbc = Convert.toDBC(a);
        log.info("dbc: {}", dbc);*/

        //7.转为16进制（Hex）字符串
        /*String a = "转为16进制（Hex）字符串";
        String hex = Convert.toHex(a, CharsetUtil.CHARSET_UTF_8);
        log.info("hex:{}", hex);*/

        //8.将16进制（Hex）字符串转为普通字符串:
        /*String hex = "e8bdace4b8ba3136e8bf9be588b6efbc88486578efbc89e5ad97e7aca6e4b8b2";
        String raw = Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8);
        log.info("raw:{}", raw);*/

        //9.Unicode和字符串转换
        /*String a = "Unicode和字符串转换";
        String unicode = Convert.strToUnicode(a);
        log.info("unicode:{}", unicode);
        String raw = Convert.unicodeToStr(unicode);
        log.info("raw:{}", raw);*/

        //10.编码转换
       /*String a = "编码转换";
        String result = Convert.convertCharset(a, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        log.info("result:{}", result);
        String raw = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");
        log.info("raw:{}", raw);*/

        //11.时间单位转换
        /*long a = 1000 * 60 * 60 * 24 * 7;
        long minutes = Convert.convertTime(a, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        log.info("minutes:{}", minutes);*/

        //12.金额大小写转换 ---- 四色五入 角柒分
        double a = 888888888888.6666;
        String digitUppercase = Convert.digitToChinese(a);
        log.info("digitUppercase:{}", digitUppercase);
    }
}
