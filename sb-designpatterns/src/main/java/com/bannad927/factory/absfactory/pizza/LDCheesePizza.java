package com.bannad927.factory.absfactory.pizza;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cbb
 * @date 2021.3.2
 */
@Slf4j
public class LDCheesePizza extends Pizza {

    @Override
    public void prepare() {
        // TODO Auto-generated method stub
        setName("伦敦的奶酪pizza");
        log.info("伦敦的奶酪pizza 准备原材料");
    }
}
