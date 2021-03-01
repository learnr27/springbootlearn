package com.bannad927.strategy;

import com.bannad927.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cbb
 * @date 2021.3.1
 */
@Component
@Slf4j
public class StrategyContext {

    private final Map<String, PayStrategy> strategyMap = new ConcurrentHashMap<>();


    @Autowired
    public StrategyContext(Map<String, PayStrategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));
    }

    public Result pay(PayInfo payInfo) {

        if (payInfo.getPaymentForm().equals(PaymentForm.H5)) {
            strategyMap.get(payInfo.getPayWay().getValue()).payH5(payInfo);
        } else {
            strategyMap.get(payInfo.getPayWay().getValue()).paySDK(payInfo);
        }

        return Result.getSuccessResult(payInfo);
    }

    public Result query(String orderNo) {
        // todo 根据orderNo去db查询 PayInfo
        PayInfo payInfo = new PayInfo();
        payInfo.setOrderNo(orderNo);
        payInfo.setPayWay(PayWay.JD_PAY);

        PayWay payWay = payInfo.getPayWay();

        strategyMap.get(payWay.getValue()).query(payInfo);
        return Result.getSuccessResult(payInfo);
    }
}
