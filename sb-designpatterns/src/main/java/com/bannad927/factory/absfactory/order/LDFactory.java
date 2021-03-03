package com.bannad927.factory.absfactory.order;

import com.bannad927.factory.absfactory.pizza.LDCheesePizza;
import com.bannad927.factory.absfactory.pizza.LDPepperPizza;
import com.bannad927.factory.absfactory.pizza.Pizza;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cbb
 * @date 2021.3.2
 */
@Slf4j
public class LDFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        log.info("~使用的是抽象工厂模式~");
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
