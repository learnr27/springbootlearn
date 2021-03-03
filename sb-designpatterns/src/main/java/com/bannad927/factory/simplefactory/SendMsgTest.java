package com.bannad927.factory.simplefactory;

import com.bannad927.factory.SendMassage;

/**
 * @author cbb
 * @date 2021.3.1
 */
public class SendMsgTest {

    SimpleFactory simpleFactory;
    SendMassage sendMassage = null;

    public SendMsgTest(SimpleFactory simpleFactory, String type) {
        this.simpleFactory = simpleFactory;
        sendMassage = this.simpleFactory.createMsg(type);
        sendMassage.sendMsg();
    }

    public static void main(String[] args) {
        new SendMsgTest(new SimpleFactory(), "dd");
    }
}
