package com.bannad927.factory.factorymethod;

/**
 * @author cbb
 * @date 2021.3.1
 */
public abstract class SendMassage {

    /**
     * 平台
     */
    protected String platformName;


    /**
     * 发送消息
     */
    public abstract void sendMsg();


    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
