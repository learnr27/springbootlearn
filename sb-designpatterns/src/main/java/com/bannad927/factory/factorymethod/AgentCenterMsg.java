package com.bannad927.factory.factorymethod;

/**
 * @author cbb
 * @date 2021.3.2
 */
public class AgentCenterMsg extends MsgCenter{

    @Override
    SendMassage createSendMsg(String type) {
        SendMassage sendMassage = null;
        if (type.equals("dd")) {
            sendMassage = new AgentCenterDingTalkMsg();
        } else if (type.equals("ali")) {
            sendMassage = new AgentCenterAliSmsMsg();
        }

        return sendMassage;
    }
}
