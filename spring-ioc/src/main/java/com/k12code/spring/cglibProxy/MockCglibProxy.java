package com.k12code.spring.cglibProxy;

import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Method;

/**
 * CGLIB 动态代理则是通过生成目标类的子类来实现的
 * @author Carl
 * @since 1.0.0
 */
public class MockCglibProxy extends Target{

    MethodInterceptor interceptor;

    public void setInterceptor(MethodInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    static Method save1;
    static Method save2;
    static Method save3;

    static {

        try {
            save1 = Target.class.getMethod("save1");
            save2 = Target.class.getMethod("save2",int.class);
            save3 = Target.class.getMethod("save3",long.class);
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    @Override
    public void save1() {
        try {
            interceptor.intercept(this,save1,new Object[0],null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save2(int i) {
        try {
            interceptor.intercept(this,save2,new Object[]{10},null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void save3(long j) {
        try {
            interceptor.intercept(this,save3,new Object[]{20L},null);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
