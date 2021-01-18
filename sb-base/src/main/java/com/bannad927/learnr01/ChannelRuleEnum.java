package com.bannad927.learnr01;

/**
 * @author: chengbinbin
 * @date: 2021.1.18
 **/
public enum ChannelRuleEnum {


    /**
     * 阿里云短信
     */
    ALISMS("AliSms", new NoticeAliSmsRule()),

    /**
     * 钉钉消息
     */
    DINGTALK("DingTalk", new NoticeDingTalkRule());

    public String name;

    public GeneralChannelRule channel;

    ChannelRuleEnum(String name, GeneralChannelRule channel) {
        this.name = name;
        this.channel = channel;
    }

    public static ChannelRuleEnum match(String name) {
        ChannelRuleEnum[] values = ChannelRuleEnum.values();
        for (ChannelRuleEnum value : values) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public GeneralChannelRule getChannel() {
        return channel;
    }
}
