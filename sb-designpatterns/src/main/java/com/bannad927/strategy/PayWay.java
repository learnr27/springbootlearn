package com.bannad927.strategy;

/**
 * @author cbb
 * @date 2021.3.1
 */
public enum PayWay {


    /**
     * 支付宝
     */
    ALI_PAY("aliPay", 0, "支付宝"),

    /**
     * 微信支付
     */
    WX_MP_PAY("wxMpPay", 1, "微信支付"),

    /**
     * 云闪付
     */
    UNION_PAY("unionPay", 2, "云闪付"),

    /**
     * 京东支付
     */
    JD_PAY("jdPay", 3, "京东支付");

    private String value;
    private int num;
    private String name;

    PayWay(String value, int num, String name) {
        this.value = value;
        this.num = num;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }


    public static PayWay getPayWay(int num) {
        for (PayWay enums : PayWay.values()) {
            if (enums.getNum() == num) {
                return enums;
            }
        }
        return null;
    }

    public static String getNameByNum(int num) {
        for (PayWay enums : PayWay.values()) {
            if (enums.getNum() == num) {
                return enums.getName();
            }
        }
        return null;
    }

    public static PayWay getPayWayByValue(String value) {
        for (PayWay enums : PayWay.values()) {
            if (enums.getValue().equals(value)) {
                return enums;
            }
        }
        return null;
    }

}
