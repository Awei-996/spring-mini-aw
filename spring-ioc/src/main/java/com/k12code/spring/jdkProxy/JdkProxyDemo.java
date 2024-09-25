package com.k12code.spring.jdkProxy;

import java.lang.reflect.Proxy;

/**
 * @author Carl
 * @since 1.0.0
 */
public class JdkProxyDemo {
    public static void main(String[] args) {
        Impl inter = new Impl();
        ClassLoader classLoader = JdkProxyDemo.class.getClassLoader();
        Inter instance = (Inter) Proxy.newProxyInstance(classLoader, new Class[]{Inter.class}, (o,method,objects) -> {
            System.err.println("before");
            // 通过反射调用 参数一：调用方法所在的对象 参数二: 调用方法所需的参数
            Object invoke = method.invoke(inter, args);
            System.err.println("after");
            return invoke;
        });
        instance.foo();
    }
}
