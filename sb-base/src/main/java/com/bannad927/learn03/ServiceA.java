package com.bannad927.learn03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: chengbinbin
 * @date: 2021.1.21
 **/
@Component
@Slf4j
public class ServiceA {

    @Resource
    private ServiceB serviceB;

    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
        log.info("A设置了B:{}");
    }
}
