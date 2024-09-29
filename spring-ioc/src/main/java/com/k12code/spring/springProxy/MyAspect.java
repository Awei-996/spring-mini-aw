package com.k12code.spring.springProxy;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 高级切面
 * @author Carl
 * @since 1.0.0
 */
@Aspect
public class MyAspect {

    @Before("execution(* foo())")
    public void before(){
        System.err.println("before");
    }

    @After("execution(* foo())")
    public void after(){
        System.out.println("after");
    }
}
