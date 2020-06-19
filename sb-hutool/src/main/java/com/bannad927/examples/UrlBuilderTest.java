package com.bannad927.examples;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chengbb
 * @date 2020.6.19
 */
@Slf4j
public class UrlBuilderTest {

    public static void main(String[] args) {
        //完整构建
        /*String buildUrl = UrlBuilder.create()
                .setScheme("https")
                .setHost("www.hutool.cn")
                .addPath("/aaa").addPath("bbb")
                .addQuery("ie", "UTF-8")
                .addQuery("wd", "test")
                .build();
        log.info("buildUrl:{}", buildUrl);*/

        //中文编码
        /*String buildUrl = UrlBuilder.create()
                .setScheme("https")
                .setHost("www.hutool.cn")
                .addPath("/s")
                .addQuery("ie", "UTF-8")
                .addQuery("ie", "GBK")
                .addQuery("wd", "测试")
                .build();
        log.info("buildUrl:{}", buildUrl);*/

        //解析
        /*UrlBuilder builder = UrlBuilder.ofHttp("www.hutool.cn/aaa/bbb/?a=张三&b=%e6%9d%8e%e5%9b%9b#frag1", CharsetUtil.CHARSET_UTF_8);
        log.info("a:{}", builder.getQuery().get("a"));
        log.info("b:{}", builder.getQuery().get("b"));*/

        //特殊URL解析
        String urlStr = "https://mp.weixin.qq.com/s?__biz=MzI5NjkyNTIxMg==&amp;mid=100000465&amp;idx=1";
        UrlBuilder builder = UrlBuilder.ofHttp(urlStr, CharsetUtil.CHARSET_UTF_8);
        log.info(":{}", builder.build());


    }
}
