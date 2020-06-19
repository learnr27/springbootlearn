package com.bannad927.examples;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.SimpleAspect;
import cn.hutool.core.date.TimeInterval;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切面代理工具
 * 原理
 * 动态代理对象的创建原理是假设创建的代理对象名为 $Proxy0：
 * <p>
 * 根据传入的interfaces动态生成一个类，实现interfaces中的接口
 * 通过传入的classloder将刚生成的类加载到jvm中。即将$Proxy0类load
 * 调用$Proxy0的$Proxy0(InvocationHandler)构造函数 创建$Proxy0的对象，并且用interfaces参数遍历其所有接口的方法，并生成实现方法，这些实现方法的实现本质上是通过反射调用被代理对象的方法。
 * 将$Proxy0的实例返回给客户端。
 * 当调用代理类的相应方法时，相当于调用 InvocationHandler.invoke(Object, Method, Object []) 方法。
 *
 * @author chengbb
 * @date 2020.6.11
 */
@Slf4j
@Component
public class ProxyUtilTest {

    public interface Animal {
        void eat();
    }

    public class Cat implements Animal {

        @Override
        public void eat() {
            log.info("猫吃鱼");
        }
    }

    public class TimeIntervalAspect extends SimpleAspect {
        //TimeInterval为Hutool实现的一个计时器
        private TimeInterval interval = new TimeInterval();

        @Override
        public boolean before(Object target, Method method, Object[] args) {
            interval.start();
            return true;
        }

        @Override
        public boolean after(Object target, Method method, Object[] args) {
            log.info("Method [{}.{}] execute spend [{}]ms", target.getClass().getName(), method.getName(), interval.intervalMs());
            return true;
        }
    }


    public void test() {
        Cat cat = new Cat();
        Animal animal = ProxyUtil.proxy(cat, TimeIntervalAspect.class);
        animal.eat();
    }
}
