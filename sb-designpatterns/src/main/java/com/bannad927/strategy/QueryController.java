package com.bannad927.strategy;

import com.bannad927.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author cbb
 * @date 2021.3.1
 */
@RestController
@RequestMapping
public class QueryController {

    @Resource
    private StrategyContext strategyContext;

    @GetMapping("/query")
    public Result query(String orderNo) throws Exception {

        return strategyContext.query(orderNo);

    }
}
