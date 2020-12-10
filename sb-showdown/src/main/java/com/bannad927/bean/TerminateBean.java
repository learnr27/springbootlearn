package com.bannad927.bean;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;

/**
 * @author: chengbinbin
 * @date: 2020/12/10
 **/
@Slf4j
public class TerminateBean {

    @PreDestroy
    public void preDestroy() {
        log.info("TerminalBean is destroyed:");
    }
}
