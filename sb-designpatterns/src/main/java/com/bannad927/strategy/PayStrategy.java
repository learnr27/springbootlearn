package com.bannad927.strategy;

/**
 * @author cbb
 * @date 2021.3.1
 */
public interface PayStrategy {

    /**
     * SDK支付
     *
     * @param info
     */
    void paySDK(PayInfo info);

    /**
     * H5支付
     *
     * @param info
     * @throws Exception
     */
    void payH5(PayInfo info);

    /**
     * 查询
     *
     * @param info
     * @throws Exception
     */
    void query(PayInfo info);

    /**
     * 退款
     *
     * @param info
     * @throws Exception
     */
    void refund(PayInfo info);

    /**
     * 关闭
     *
     * @param info
     * @throws Exception
     */
    void close(PayInfo info);

    /**
     * 通知
     *
     * @param info
     * @return
     * @throws Exception
     */
    PayInfo notify(PayInfo info);

}
