package com.bannad927.learnr01;

/**
 * 通过枚举来巧妙干掉if-else的方案
 *
 * @author: chengbinbin
 * @date: 2021.1.18
 **/
public class Learn01Test {

    public static void main(String[] args) {
        String sign = "AliSms";
        NotifyReqData reqData = new NotifyReqData();
        reqData.setTemplateCode(sign);
        ChannelRuleEnum channelRule = ChannelRuleEnum.match(sign);
        GeneralChannelRule rule = channelRule.channel;
        rule.process(reqData);
    }
}
