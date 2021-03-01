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
public class JdPay implements PayStrategy {

    @Override
    public void paySDK(PayInfo info) {
        // todo SDK
    }

    @Override
    public void payH5(PayInfo info) {
        info.setPayPlatformOrderNo(IdUtil.fastSimpleUUID());
        info.setPayData("{\"signData\":\"c5a715d78b1ebea3bf927e24970597b3\",\"orderId\":\"202010281729482015590559917371\"");

    }

    @Override
    public void query(PayInfo info) {
        // todo 查询接口
        info.setPayData("JdPay");
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
