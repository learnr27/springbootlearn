package com.bannad927.learnr01;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: chengbinbin
 * @date: 2021.1.18
 **/
@Slf4j
public class NoticeAliSmsRule extends GeneralChannelRule {
    @Override
    public void process(NotifyReqData reqData) {
        // TODO: 阿里云短信逻辑
        log.info("发送阿里云短信消息:{}",reqData.getTemplateCode());
    }
}
