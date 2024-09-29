package com.k12code.spring.springProxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定义低级一个切面
 * @author Carl
 * @since 1.0.0
 */
@Configuration
public class Config {

    @Bean
    public Advisor advisor(MethodInterceptor advice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");
        return new DefaultPointcutAdvisor(pointcut,advice);
    }

    @Bean
    public MethodInterceptor advice(){
        return invocation -> {
            System.err.println("before");
            Object proceed = invocation.proceed();
            System.err.println("after");
            return proceed;
        };
    }
}
