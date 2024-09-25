package com.k12code.spring.cglibProxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Carl
 * @since 1.0.0
 */
public class TestProxy {
    public static void main(String[] args) {
        Target target = new Target();
        MockCglibProxy mockCglibProxy = new MockCglibProxy();
        mockCglibProxy.setInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                return method.invoke(target,objects);
            }
        });
        target.save1();
        target.save2(0);
        target.save3(0);

    }
}
