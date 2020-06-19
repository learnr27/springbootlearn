package com.bannad927.examples;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(cn.hutool.extra.spring.SpringUtil.class)


/**
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
@ComponentScan(basePackages={"cn.hutool.extra.spring"})
public class ExtraTest {

    public static void main(String[] args) {
        //1.邮件工具-MailUtil
        /*String send = MailUtil.send("1004529325@qq.com", "测试", "邮件来自Hutool测试", false);
        log.info("send:{}", send);*/

        //2.二维码工具-QrCodeUtil
        /*// 生成指定url对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate("http://www.chengbinbin.cn/", 300, 300, FileUtil.file("D:/qrcode.jpg"));

        //基本参数设定
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.CYAN);
        // 设置背景色（灰色）
        config.setBackColor(Color.GRAY);
        // 生成二维码到文件，也可以到流
        QrCodeUtil.generate("http://www.chengbinbin.cn/", config, FileUtil.file("D:/qrcodeconfig.jpg"));

        //附带logo小图标
        QrCodeUtil.generate(//
                "http://www.chengbinbin.cn/", //二维码内容
                QrConfig.create().setImg("D:/line.png"), //附带logo
                FileUtil.file("D:/qrcodeWithLogo.jpg")//写出到的文件
        );

        //调整纠错级别
        QrConfig config1 = new QrConfig();
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        QrCodeUtil.generate("http://www.chengbinbin.cn/", config1, FileUtil.file("D:/qrcodeCustom.jpg"));

        //识别二维码
        String decode = QrCodeUtil.decode(FileUtil.file("d:/qrcode.jpg"));
        log.info("decode:{}", decode);*/

        //3.模板引擎封装-TemplateUtil
        /*//自动根据用户引入的模板引擎库的jar来自动选择使用的引擎
        //TemplateConfig为模板引擎的选项，可选内容有字符编码、模板路径、模板加载方式等，默认通过模板字符串渲染
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());

        //假设我们引入的是Beetl引擎，则：
        Template template = engine.getTemplate("Hello ${name}");
        //Dict本质上为Map，此处可用Map
        String result = template.render(Dict.create().set("name", "Hutool"));*/

        //4.Emoji工具-EmojiUtil
        /*String alias = EmojiUtil.toAlias("😄");
        log.info("转义Emoji字符:{}", alias);
        String emoji = EmojiUtil.toUnicode(":smile:");
        log.info("将转义的别名转为Emoji字符:{}", emoji);
        String aliasHttp = EmojiUtil.toHtml("😄");
        log.info("将字符串中的Unicode Emoji字符转换为HTML表现形式:{}", aliasHttp);*/

        //5.中文分词封装-TokenizerUtil -------XXX
        /*TokenizerEngine engine = TokenizerUtil.createEngine();
        String text = "这两个方法的区别在于返回值";
        Result result = engine.parse(text);
        String resultStr = CollUtil.join((Iterator<Word>)result, " ");
        log.info("自动根据用户引入的分词库的jar来自动选择使用的引擎:{}", resultStr);

        TokenizerEngine engine = new HanLPEngine();
        String text = "这两个方法的区别在于返回值";
        Result result = engine.parse(text);
        String resultStr = CollUtil.join((Iterator<Word>) result, " ");
        log.info("自定义模板引擎 HanLP为例 :{}", resultStr);*/

        //6.Spring工具-SpringUtil
        final Demo2 testDemo = SpringUtil.getBean("testDemo");
        log.info("testDemo:{}", testDemo.getId());
    }

    @Data
    public static class Demo2{
        private long id;
        private String name;

        @Bean(name="testDemo")
        public Demo2 generateDemo() {
            Demo2 demo = new Demo2();
            demo.setId(12345);
            demo.setName("test");
            return demo;
        }
    }
}
