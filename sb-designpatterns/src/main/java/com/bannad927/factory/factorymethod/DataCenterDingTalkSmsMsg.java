package com.bannad927.factory.factorymethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cbb
 * @date 2021.3.2
 */
@Slf4j
public class DataCenterDingTalkSmsMsg extends SendMassage{

    @Override
    public void sendMsg() {
        setPlatformName("数据中心钉钉消息");
        log.info("数据中心钉钉消息");
    }
}
