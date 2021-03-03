package com.bannad927.factory.factorymethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cbb
 * @date 2021.3.2
 */
@Slf4j
public class AgentCenterAliSmsMsg extends SendMassage {


    @Override
    public void sendMsg() {
        setPlatformName("代理商阿里云短信消息");
        log.info("代理商阿里云短信消息");
    }
}
