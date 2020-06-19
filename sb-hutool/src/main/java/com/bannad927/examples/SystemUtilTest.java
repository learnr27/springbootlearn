package com.bannad927.examples;

import cn.hutool.system.SystemUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统属性调用
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
public class SystemUtilTest {

    public static void main(String[] args) {
        log.info("Java Virtual Machine Specification信息:\n{}", SystemUtil.getJvmSpecInfo());
        log.info("Java Virtual Machine Implementation信息:\n{}",SystemUtil.getJvmInfo());
        log.info("Java Specification信息:{}\n",SystemUtil.getJavaSpecInfo());
        log.info("Java Implementation信息:{}\n",SystemUtil.getJavaInfo());
        log.info("Java运行时信息:{}\n",SystemUtil.getJavaRuntimeInfo());
        log.info("系统信息:{}\n",SystemUtil.getOsInfo());
        log.info("用户信息:{}\n",SystemUtil.getUserInfo());
        log.info("当前主机网络地址信息:{}\n",SystemUtil.getHostInfo());
        log.info("运行时信息，包括内存总大小、已用大小、可用大小等:{}\n",SystemUtil.getRuntimeInfo());
        log.info("PID:{}\n",SystemUtil.getCurrentPID());
        log.info("getTotalThreadCount:{}\n",SystemUtil.getTotalThreadCount());
    }
}
