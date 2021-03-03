package com.bannad927.factory.absfactory.order;

import com.bannad927.factory.absfactory.pizza.BJCheesePizza;
import com.bannad927.factory.absfactory.pizza.BJPepperPizza;
import com.bannad927.factory.absfactory.pizza.Pizza;
import lombok.extern.slf4j.Slf4j;

/**
 * 工厂子类
 * @author cbb
 * @date 2021.3.2
 */
@Slf4j
public class BJFactory implements AbsFactory{

    @Override
    public Pizza createPizza(String orderType) {
        log.info("~使用的是抽象工厂模式~");
        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
