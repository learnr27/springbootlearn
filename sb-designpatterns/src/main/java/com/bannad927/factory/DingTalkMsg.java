package com.bannad927.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cbb
 * @date 2021.3.1
 */
@Slf4j
public class DingTalkMsg extends SendMassage {

    @Override
    public void sendMsg() {
        log.info("发送钉钉消息");
    }
}
