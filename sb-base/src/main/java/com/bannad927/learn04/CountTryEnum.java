package com.bannad927.learn04;

import lombok.Getter;

/**
 * @author cbb
 * @date 2021.2.5
 */
public enum CountTryEnum {

    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");

    @Getter
    private Integer retCode;

    @Getter
    private String retMessage;

    CountTryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountTryEnum forEachCountTryEnum(int index) {

        CountTryEnum[] values = CountTryEnum.values();
        for (CountTryEnum element:values) {
            if (index == element.getRetCode()) {
                return element;
            }
        }
        return null;
    }
}
