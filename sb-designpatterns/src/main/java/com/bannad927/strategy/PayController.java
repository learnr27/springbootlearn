package com.bannad927.strategy;

import com.bannad927.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cbb
 * @date 2021.3.1
 */
@RestController
@RequestMapping
public class PayController {

    @Resource
    private StrategyContext strategyContext;


    /**
     * {
     * "paymentForm":"APP",
     * "payWay": 0,
     * "orderNo": "20211614582366",
     * "amount": "0.01"
     * }
     *
     * @param payRecord
     * @return
     * @throws Exception
     */
    @PostMapping("/pay")
    public Result pay(@RequestBody PayInfo payRecord) throws Exception {
        return strategyContext.pay(payRecord);
    }
}
