package com.bannad927.factory.factorymethod;

/**
 * @author cbb
 * @date 2021.3.2
 */
public abstract  class MsgCenter {

    abstract SendMassage createSendMsg(String type);

}
