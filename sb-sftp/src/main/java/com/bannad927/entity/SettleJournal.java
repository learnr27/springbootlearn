package com.bannad927.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易明细
 *
 * @author chengbb
 * @date 2020.7.15
 */
@Data
public class SettleJournal {

    /**
     * 集团号
     */
    private String groupNumber;

    /**
     * 企业用户号
     */
    private String enterpriseUserNumber;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户指令id
     */
    private String merchantInstructionId;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 订单日期
     */
    private Date orderDate;

    /**
     * 退票金额
     */
    private BigDecimal refundAmount;

    /**
     * 退票日期
     */
    private Date refundDate;

    /**
     * 银行账户号
     */
    private String bankAccountNumber;

    /**
     * 银行账户名
     */
    private String bankAccountName;

    /**
     * 退票银行行号
     */
    private String refundBankNumber;

    /**
     * 退票银行名称
     */
    private String refundBankName;

    /**
     * 退票原因
     */
    private String reasonForRefund;

    /**
     * 退票类型
     */
    private String refundType;
}
