package com.bannad927.factory.absfactory.order;

import com.bannad927.factory.absfactory.pizza.Pizza;

/**
 * 一个抽象工厂模式的抽象层(接口)
 *
 * @author cbb
 * @date 2021.3.2
 */
public interface AbsFactory {

    /**
     * 让下面的工厂子类来 具体实现
     *
     * @param orderType
     * @return
     */
    Pizza createPizza(String orderType);
}
