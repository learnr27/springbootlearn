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
public class ServiceB {

    @Resource
    private ServiceA serviceA;

    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
        log.info("A设置了B:{}");
    }
}
