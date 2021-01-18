package com.bannad927.learnr01;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: chengbinbin
 * @date: 2021.1.18
 **/
@Slf4j
public class NoticeDingTalkRule extends GeneralChannelRule {

    @Override
    public void process(NotifyReqData reqData) {
        // TODO: 钉钉逻辑
        log.info("发送钉钉消息:{}", reqData.getTemplateCode());
    }
}
