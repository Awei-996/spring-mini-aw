package com.k12code.spring.springProxy;

import com.k12code.spring.cglibProxy.Target;
import com.k12code.spring.jdkProxy.Impl;
import com.k12code.spring.jdkProxy.Inter;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author Carl
 * @since 1.0.0
 */
public class AspectTest {
    public static void main(String[] args) {
        // 创建切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");

        // 环绕通知
        MethodInterceptor methodInterceptor = (methodInvocation) -> {
            System.err.println("环绕通知前：before");
            Object proceed = methodInvocation.proceed();
            System.err.println("环绕通知后：after");
            return proceed;
        };
        // 创建切面
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(pointcut, methodInterceptor);

        // 创建代理工厂

        Target1 target1 = new Target1();

        ProxyFactory proxyFactory = new ProxyFactory();
        // 手动设置这个类有没有接口
        proxyFactory.setInterfaces(target1.getClass().getInterfaces());
        // ProxyTargetClass 默认是false
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvisor(defaultPointcutAdvisor);
        proxyFactory.setTarget(target1);

        // 查看代理的方式
        I1 proxy = (I1) proxyFactory.getProxy();
        proxy.foo();
        proxy.bar();
        System.err.println(proxy.getClass());

    }

    interface I1 {
        void foo();
        void bar();
    }

    static class Target1 implements I1{

        @Override
        public void foo() {
            System.out.println("foo");
        }

        @Override
        public void bar() {
            System.err.println("bar");
        }
    }
    static class Target2 {

        public void foo() {
            System.out.println("foo");
        }

        public void bar() {
            System.err.println("bar");
        }
    }
}
