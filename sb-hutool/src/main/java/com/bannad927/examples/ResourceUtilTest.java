package com.bannad927.examples;

import cn.hutool.core.io.resource.ResourceUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 资源工具
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class ResourceUtilTest {

    public static void main(String[] args) {
        String str = ResourceUtil.readUtf8Str("application.yml");
        log.info("str:{}", str);

    }
}
