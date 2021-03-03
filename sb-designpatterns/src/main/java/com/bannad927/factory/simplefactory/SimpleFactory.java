package com.bannad927.factory.simplefactory;

import com.bannad927.factory.AliSmsMsg;
import com.bannad927.factory.DingTalkMsg;
import com.bannad927.factory.EmailMsg;
import com.bannad927.factory.SendMassage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cbb
 * @date 2021.3.1
 */
@Slf4j
public class SimpleFactory {

    public SendMassage createMsg(String type) {
        SendMassage massage = null;
        log.info("使用简单工厂模式");
        if (type.equals("aliyun")) {
            massage = new AliSmsMsg();
        } else if (type.equals("dd")) {
            massage = new DingTalkMsg();
        } else if (type.equals("email")) {
            massage = new EmailMsg();
        }
        return massage;
    }

    public static SendMassage createMsg2(String type) {
        SendMassage massage = null;
        log.info("使用静态工厂模式");
        if (type.equals("aliyun")) {
            massage = new AliSmsMsg();
        } else if (type.equals("dd")) {
            massage = new DingTalkMsg();
        } else if (type.equals("email")) {
            massage = new EmailMsg();
        }
        return massage;
    }

}
