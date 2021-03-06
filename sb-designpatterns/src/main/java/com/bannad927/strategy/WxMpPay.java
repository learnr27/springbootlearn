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
public class WxMpPay implements PayStrategy {

    @Override
    public void paySDK(PayInfo info) {
        info.setPayPlatformOrderNo(IdUtil.fastSimpleUUID());
        info.setPayData("{\"package\":\"Sign=WXPay\",\"success\":true,\"appid\":\"wxf");
    }

    @Override
    public void payH5(PayInfo info) {
        info.setPayPlatformOrderNo(IdUtil.fastSimpleUUID());
        info.setPayData("{\"package\":\"Sign=WXPay\",\"success\":true,\"appid\":\"wxf");

    }

    @Override
    public void query(PayInfo info) {
        // todo 查询接口
        info.setPayData("WxMpPay.....");
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
