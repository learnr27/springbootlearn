package com.bannad927.examples;

import cn.hutool.core.net.multipart.UploadFile;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Hutool-http针对JDK的HttpUrlConnection做一层封装，简化了HTTPS请求、文件上传、Cookie记忆等操作，使Http请求变得无比简单。
 * <p>
 * Hutool-http的核心集中在两个类：
 * <p>
 * HttpRequest
 * HttpResponse
 *
 * @author chengbb
 * @date 2020.6.17
 */
@Slf4j
public class HttpTest {

    /**
     * Hutool-http优点
     * 根据URL自动判断是请求HTTP还是HTTPS，不需要单独写多余的代码。
     * 表单数据中有File对象时自动转为multipart/form-data表单，不必单做做操作。
     * 默认情况下Cookie自动记录，比如可以实现模拟登录，即第一次访问登录URL后后续请求就是登录状态。
     * 自动识别304跳转并二次请求
     * 自动识别页面编码，即根据header信息或者页面中的相关标签信息自动识别编码，最大可能避免乱码。
     * 自动识别并解压Gzip格式返回内容
     */

    public static void main(String[] args) {
        //1.GET请求
        /*String content = HttpUtil.get("http://www.chengbinbin.cn");
        log.info("content:{}", content);*/

        //2.POST请求
        /*HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("deviceName", "0037272");

        String result1 = HttpUtil.post("http://ssingStatus", paramMap);
        log.info("result1:{}", result1);*/

        //3.文件上传
        /*HashMap<String, Object> paramMap = new HashMap<>();
        //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
        paramMap.put("file", FileUtil.file("D:\\elastic.jpg"));

        String result= HttpUtil.post("http://xxxxx/upload/file", paramMap);
        log.info("result:{}", result);*/

        /*HashMap<String, Object> paramMap = new HashMap<>();
        //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
        paramMap.put("file", FileUtil.file("D:\\elastic.jpg"));

        String result= HttpRequest.post("http://xxxxx.com/marketback/upload/file")
                .header("Authorization","XZGzHXNX8VhMG")
                .form(paramMap).execute().body();
        log.info("result:{}", result);*/

        //4.下载文件
        //String fileUrl = "http://*****/images/5e399d90-059e-41d6-8770-91c77096a10a.jpg";

        /*//将文件下载后保存在E盘，返回结果为下载文件大小
        long size = HttpUtil.downloadFile(fileUrl, FileUtil.file("e:/"));
        log.info("Download size:{} ", size);*/

       /* //带进度显示的文件下载
        HttpUtil.downloadFile(fileUrl, FileUtil.file("e:/"), new StreamProgress(){

            @Override
            public void start() {
                log.info("开始下载。。。。");
            }

            @Override
            public void progress(long progressSize) {
                log.info("已下载：{}", FileUtil.readableFileSize(progressSize));
            }

            @Override
            public void finish() {
                log.info("下载完成！");
            }
        });*/

        //5.Http请求-HttpRequest
        //链式构建请求
        /*HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", "249");
        String result2 = HttpRequest.post("http://**ack/rewardCoupon/findOne")
                .header(Header.AUTHORIZATION, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJleHAiOjE1OTI0NjkwNjksInVzZXJJZCI6IjE1IiwiaWF0IjoxNTkyMzgyNjY5LCJwbGF0Zm9ybSI6Im1hbmFnZXIifQ.D9DEqJuVHmfVm1B73dfqLFsTXRil6LiM_kFlZoh0AkUez89cxkiXZGzHXNX8VhMG")//头信息，多个头信息多次调用此方法即可
                .header(Header.CONTENT_TYPE, "application/json;charset=UTF-8")
                .timeout(20000)//超时，毫秒
                .body(JSONUtil.toJsonStr(paramMap))
                .execute().body();
        log.info("result2:{}", result2);*/

        //6.Http响应-HttpResponse
        /*HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", "249");
        HttpResponse result2 = HttpRequest.post("http://om/rewardCoupon/findOne")
                .header(Header.AUTHORIZATION, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJleHAiOjE1OTI0NjkwNjksInVzZXJJZCI6IjE1IiwiaWF0IjoxNTkyMzgyNjY5LCJwbGF0Zm9ybSI6Im1hbmFnZXIifQ.D9DEqJuVHmfVm1B73dfqLFsTXRil6LiM_kFlZoh0AkUez89cxkiXZGzHXNX8VhMG")//头信息，多个头信息多次调用此方法即可
                .header(Header.CONTENT_TYPE, "application/json;charset=UTF-8")
                .timeout(20000)//超时，毫秒
                .body(JSONUtil.toJsonStr(paramMap))
                .execute();
        log.info("result2:{}", result2);
        log.info("res.getStatus:{}", result2.getStatus());
        log.info("res.getStatus==200:{}", result2.getStatus()== HttpStatus.HTTP_OK);
        log.info("res.AUTHORIZATION:{}", result2.header(Header.AUTHORIZATION));
        log.info("res.body:{}", result2.body());
        log.info("res.contentEncoding:{}", result2.contentEncoding());
        log.info("res.Content-Disposition:{}", result2.header("Content-Disposition"));*/

        //7.HTML工具类-HtmlUtil
        /*//还原被转义的HTML特殊字符
        String html = "<html><body>123'123'</body></html>";
        String escape = HtmlUtil.escape(html);
        log.info("escape:{}", escape);

        //清除指定HTML标签和被标签包围的内容
        String str = "pre<img src=\"xxx/dfdsfds/test.jpg\">";
        String result = HtmlUtil.removeHtmlTag(str, "img");
        log.info("result:{}", result);

        //清除所有HTML标签，但是保留标签内的内容
        String str1 = "pre<div class=\"test_div\">\r\n\t\tdfdsfdsfdsf\r\n</div><div class=\"test_div\">BBBB</div>";
        String result1 = HtmlUtil.cleanHtmlTag(str1);
        log.info("result2:{}", result1);

        //清除指定HTML标签，不包括内容
        String str2 = "pre<div class=\"test_div\">abc</div>";
        String result2 = HtmlUtil.unwrapHtmlTag(str2, "div");
        log.info("result2:{}", result2);

        //去除HTML标签中的指定属性，如果多个标签有相同属性，都去除
        String html3 = "<div class=\"test_div\"></div><span class=\"test_div\"></span>";
        String result3 = HtmlUtil.removeHtmlAttr(html3, "class");
        log.info("result3:{}", result3);

        //去除指定标签的所有属性
        String html4 = "<div class=\"test_div\" width=\"120\"></div>";
        String result4 = HtmlUtil.removeAllHtmlAttr(html4, "div");
        log.info("result4:{}", result4);

        //HtmlUtil.filter 过滤HTML文本，防止XSS攻击
        String html5 = "<alert>321</alert>";
        String filter5 = HtmlUtil.filter(html5);
        log.info("filter5:{}", filter5);*/

        //8.UA工具类-UserAgentUtil
        /*String uaStr = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1";
        UserAgent ua = UserAgentUtil.parse(uaStr);
        log.info("getBrowser:{}", ua.getBrowser().toString());
        log.info("getVersion:{}", ua.getVersion());
        log.info("getEngine:{}", ua.getEngine().toString());
        log.info("getEngineVersion:{}", ua.getEngineVersion());
        log.info("getOs:{}", ua.getOs().toString());
        log.info("getPlatform:{}", ua.getPlatform().toString());
        log.info("isMobile:{}", ua.isMobile());*/

        //9.Soap客户端-SoapClient
        /*SoapClient client = SoapClient.create("http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx")
                // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间
                .setMethod("web:getCountryCityByIp", "http://www.chengbinbin.cn/")
                // 设置参数，此处自动添加方法的前缀：web
                .setParam("theIpAddress", "218.21.240.106");
        log.info("==>:{}", client.send(true));*/

        //10.简易Http服务器-SimpleServer
        /*HttpUtil.createServer(8888)
                .addAction("/", (req, res)->{
                    res.write("Hello Hutool Server");
                })
                .start();*/

        //简单的文件服务器 Hutool默认提供了简单的文件服务，即定义一个root目录，则请求路径后直接访问目录下的资源，默认请求index.html，类似于Nginx。
        /*HttpUtil.createServer(8888)
                // 设置默认根目录
                .setRoot("D:\\")
                .start();*/

        //读取请求和返回内容
       /* HttpUtil.createServer(8888)
                // 返回JSON数据测试
                .addAction("/restTest", (request, response) ->
                        response.write("{\"id\": 1, \"msg\": \"OK\"}", ContentType.JSON.toString())
                ).start();*/

        //获取表单数据并返回
        /*HttpUtil.createServer(8888)
                // http://localhost:8888/formTest?a=1&a=2&b=3
                .addAction("/formTest", (request, response) ->
                        response.write(request.getParams().toString(), ContentType.TEXT_PLAIN.toString())
                ).start();*/

        //文件上传
        HttpUtil.createServer(8888)
                .addAction("/file", (request, response) -> {
                            final UploadFile file = request.getMultipart().getFile("file");
                            // 传入目录，默认读取HTTP头中的文件名然后创建文件
                            file.write("d:/test/");
                            response.write("OK!", ContentType.TEXT_PLAIN.toString());
                        }
                )
                .start();

    }
}
