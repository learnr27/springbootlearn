package com.bannad927.factory.absfactory.pizza;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cbb
 * @date 2021.3.2
 */
@Slf4j
public class BJPepperPizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("北京的胡椒pizza");
        log.info("北京的胡椒pizza 准备原材料");
    }
}
