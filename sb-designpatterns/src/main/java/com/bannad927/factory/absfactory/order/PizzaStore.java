package com.bannad927.factory.absfactory.order;

/**
 * @author cbb
 * @date 2021.3.2
 */
public class PizzaStore {

    public static void main(String[] args) {
        new OrderPizza(new BJFactory());
    }
}
