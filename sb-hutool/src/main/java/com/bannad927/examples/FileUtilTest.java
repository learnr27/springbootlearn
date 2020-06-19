package com.bannad927.examples;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 文件工具类
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class FileUtilTest {


    public static void main(String[] args) {
        FileUtil.mkdir("D:/test0611");
        FileUtil.touch("D:/test0611/test.txt");
        File[] files = FileUtil.ls("D:/test0611");
        for (File file : files) {
            log.info("file.getName:{}", file.getName());
        }

        FileUtil.copy("D:/test.txt", "D:/test0611/test.txt", false);

        String name = FileUtil.getName("D:/test0611/test.txt");
        log.info("name:{}", name);
        boolean isEmpty = FileUtil.isEmpty(new File("D:/test0611"));
        log.info("isEmpty:{}", isEmpty);
        String getMimeType = FileUtil.getMimeType("D:/test0611/test.txt");
        log.info("getMimeType:{}", getMimeType);
        String extName = FileUtil.extName("D:/test0611/test.txt");
        log.info("extName:{}", extName);
        boolean del = FileUtil.del("D:/test0611/test.txt");
        log.info("del:{}", del);

    }

}
