package com.bannad927.strategy;

/**
 * @author cbb
 * @date 2021.3.1
 */
public enum PaymentForm {

    APP("APP"),

    H5("H5");

    private String value;

    PaymentForm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
