package com.bannad927.factory.absfactory.pizza;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cbb
 * @date 2021.3.2
 */
@Slf4j
public class LDPepperPizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("伦敦的胡椒pizza");
        log.info("伦敦的胡椒pizza 准备原材料");
    }
}
