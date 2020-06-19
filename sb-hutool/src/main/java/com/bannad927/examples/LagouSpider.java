package com.bannad927.examples;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.*;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

/**
 * @author chengbb
 * @date 2020.6.17
 */
@Slf4j
public class LagouSpider {

    public static void main(String[] args) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", "249");
        HttpResponse result2 = HttpRequest.get("https://gate.lagou.com/v1/neirong/kaiwu/getCourseLessonDetail?lessonId=1721")
                .header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")//头信息，多个头信息多次调用此方法即可
                .header(Header.COOKIE, "sajssdk_2015_cross_new_user=1; sensorsdata2015session=%7B%7D; LG_HAS_LOGIN=1; X_HTTP_TOKEN=42daf4b72327b2814775832951bf5e71415983ed09; LG_LOGIN_USER_ID=a2cf2dfa714e3e9c9d12d7bd5bea1e1819f074194c3006c30341c3fcfa1c700f; _putrc=81B2AB5EFA26129A123F89F2B170EADC; login=true; unick=%E7%94%A8%E6%88%B73723; kw_login_authToken=\"gE/81J3gyBFAG3JY9pbgZdyZ8QKvm+7gIOGecXH7VzeMpWpN//VFy2hLUhqdvFl0JqfeXqqxubjDJoHAePFawW6kBZ+DiNMmnPN4yBmtoTNpXHne6xZOap1AlFV/sWNNwx5Yl1rr26okuEZ+6WNkFemNVmtWZ5gXW5meZnGL2Dt4rucJXOpldXhUiavxhcCELWDotJ+bmNVwmAvQCptcy5e7czUcjiQC32Lco44BMYXrQ+AIOfEccJKHpj0vJ+ngq/27aqj1hWq8tEPFFjdnxMSfKgAnjbIEAX3F9CIW8BSiMHYmPBt7FDDY0CCVFICHr2dp5gQVGvhfbqg7VzvNsw==\"; gate_login_token=8e8ceb635d88480ded2c9d81bc83804c9af5ca3b39add9d177f8019d1db44a9c; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216387554%22%2C%22first_id%22%3A%22172c19bf489431-0bce000615addc-f7d1d38-2073600-172c19bf48a2eb%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24os%22%3A%22Windows%22%2C%22%24browser%22%3A%22Chrome%22%2C%22%24browser_version%22%3A%2283.0.4103.61%22%7D%2C%22%24device_id%22%3A%22172c196793e660-032e6ee234ae7c-f7d1d38-2073600-172c196793f927%22%7D")
                .header("accept", "application/json, text/plain, */*")
                .header("content-type", "application/json;charset=UTF-8")
                .header("accept-encoding", "gzip, deflate, br")
                .header("accept-language", "zh-CN,zh;q=0.9,en;q=0.8")
                .header("origin", "https://kaiwu.lagou.com")
                .header("referer", "https://kaiwu.lagou.com/course/courseInfo.htm?courseId=50")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-site")
                .header("x-l-req-header", "{deviceType:1}")
                .timeout(20000)
                .method(Method.GET)
                .body(JSONUtil.toJsonStr(paramMap)).execute();
        log.info("result2:{}", result2);
        log.info("res.getStatus:{}", result2.getStatus());
        log.info("res.getStatus==200:{}", result2.getStatus()== HttpStatus.HTTP_OK);
        log.info("res.AUTHORIZATION:{}", result2.header(Header.AUTHORIZATION));
        log.info("res.body:{}", result2.body());
        log.info("res.contentEncoding:{}", result2.contentEncoding());
        log.info("res.Content-Disposition:{}", result2.header("Content-Disposition"));

        String listContent = result2.body();
        //使用正则获取所有标题
        List<String> titles = ReUtil.findAll("<span style=\\\"color: rgb(63, 63, 63); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;; font-size: 16px;\\\">(.*?)<span>", listContent, 1);
        for (String title : titles) {
            //打印标题
            Console.log(title);
        }
    }
}
