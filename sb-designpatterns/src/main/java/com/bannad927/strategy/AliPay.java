package com.bannad927.strategy;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author cbb
 * @date 2021.3.1
 */
@Service
@Slf4j
public class AliPay implements PayStrategy {

    @Override
    public void paySDK(PayInfo info) {
        info.setPayPlatformOrderNo(IdUtil.fastSimpleUUID());
        info.setPayData("alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=");
    }

    @Override
    public void payH5(PayInfo info) {
        info.setPayPlatformOrderNo(IdUtil.fastSimpleUUID());
        info.setPayData("<form name=\"punchout_form\" method=\"post\" action=\"https://openapi.alipay.com/gateway.do?charset=utf-8&method=alipay.trade.wap.pay&sign=");

    }

    @Override
    public void query(PayInfo info) {
        // todo 查询接口
        info.setPayData("AliPay");
    }

    @Override
    public void refund(PayInfo info) {
        // todo 退款接口
    }

    @Override
    public void close(PayInfo info) {
        // todo 关闭接口
    }

    @Override
    public PayInfo notify(PayInfo info) {
        // todo 通知接口
        return null;
    }
}
