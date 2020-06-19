package com.bannad927.examples;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * 图形验证码
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class CaptchaTest {

    public static void main(String[] args) {
        //1.线段干扰的验证码
        /*//定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        //图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("d:/line.png");
        //输出code
        log.info("输出code:{}", lineCaptcha.getCode());
        boolean verify = lineCaptcha.verify("1234");
        log.info("verify:{}", verify);
        //重新生成验证码
        lineCaptcha.createCode();
        lineCaptcha.write("d:/line.png");
        //新的验证码
        log.info("输出code:{}", lineCaptcha.getCode());
        boolean verify1 = lineCaptcha.verify("1234");
        log.info("verify1:{}", verify1);*/

        //2.圆圈干扰验证码
        /*//定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        //CircleCaptcha captcha = new CircleCaptcha(200, 100, 4, 20);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("d:/circle.png");
        //验证图形验证码的有效性，返回boolean值
        boolean verify = captcha.verify("1234");
        log.info("verify:{}", verify);*/

        //3.扭曲干扰验证码
        /*// 定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("d:/shear.png");*/

        //4.自定义验证码
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        lineCaptcha.setGenerator(randomGenerator);
        // 重新生成code
        lineCaptcha.createCode();
        lineCaptcha.write("d:/random.png");

        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
        // 自定义验证码内容为四则运算方式
        captcha.setGenerator(new MathGenerator());
        // 重新生成code
        captcha.createCode();
        captcha.write("d:/captcha.png");
        String code = captcha.getCode();
        log.info("captcha:{}", code);
        log.info("captcha:{}", captcha.verify(code));


    }
}

