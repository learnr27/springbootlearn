package com.bannad927.strategy;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author cbb
 * @date 2021.3.1
 */
@Data
public class PayInfo {

    /**
     * 支付方式
     */
    private PayWay payWay;

    /**
     * 订单来源
     */
    private PaymentForm paymentForm;

    /**
     * 业务服务订单号
     */
    private String orderNo;

    /**
     * 支付信息
     */
    private String payData;

    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 支付平台订单号
     */
    private String payPlatformOrderNo;
}
