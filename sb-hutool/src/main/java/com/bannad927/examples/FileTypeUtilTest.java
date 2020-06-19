package com.bannad927.examples;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 文件类型判断-
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class FileTypeUtilTest {
    public static void main(String[] args) {
        File file = FileUtil.file("d:/test.txt");
        String type = FileTypeUtil.getType(file);
        log.info("type:{}", type);
    }
}
