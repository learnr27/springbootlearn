package com.bannad927.factory.simplefactory;

import com.bannad927.factory.SendMassage;

/**
 * @author cbb
 * @date 2021.3.1
 */
public class SendMsgTest2 {

    SendMassage sendMassage = null;

    public SendMsgTest2(String type) {
        sendMassage = SimpleFactory.createMsg2(type);
        sendMassage.sendMsg();
    }

    public static void main(String[] args) {
        new SendMsgTest2("aliyun");
    }
}
