package com.bannad927.factory.factorymethod;

/**
 * @author cbb
 * @date 2021.3.2
 */
public class MsgTest {

    public static void main(String[] args) {
        String type = "agent";
        if (type.equals("agent")) {

            AgentCenterMsg agentCenterMsg = new AgentCenterMsg();
            agentCenterMsg.createSendMsg("dd");
        } else {

            new DataCenterMsg();
        }

    }

}
