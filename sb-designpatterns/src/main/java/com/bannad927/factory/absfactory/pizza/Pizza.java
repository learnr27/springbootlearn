package com.bannad927.factory.absfactory.pizza;

import lombok.extern.slf4j.Slf4j;

/**
 * 将Pizza 类做成抽象
 *
 * @author cbb
 * @date 2021.3.2
 */
@Slf4j
public abstract class Pizza {

    /**
     * 名字
     */
    protected String name;

    /**
     * 准备原材料, 不同的披萨不一样，因此，我们做成抽象方法
     */
    public abstract void prepare();


    public void bake() {
        log.info("{}:baking", name);
    }

    public void cut() {
        log.info("{}:cutting", name);
    }

    public void box() {
        log.info("{}:boxing", name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
