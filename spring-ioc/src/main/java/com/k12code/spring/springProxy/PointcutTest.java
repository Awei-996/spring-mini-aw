package com.k12code.spring.springProxy;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Carl
 * @since 1.0.0
 */
public class PointcutTest {

    public static void main(String[] args) throws NoSuchMethodException {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");
        // 判断当前方法是否满足上面的表达式
        System.out.println(pointcut.matches(Target1.class.getMethod("foo"), Target1.class));
        System.out.println(pointcut.matches(Target1.class.getMethod("bar"), Target1.class));

        // 注解切入点
        AspectJExpressionPointcut pointcut1 = new AspectJExpressionPointcut();
        pointcut1.setExpression("@annotation(org.springframework.transaction.annotation.Transactional)");
        System.out.println(pointcut1.matches(Target1.class.getMethod("t1"), Target1.class));
        System.out.println(pointcut1.matches(Target1.class.getMethod("bar"), Target1.class));

    }

    interface I1 {
        void foo();
        void bar();
    }

    static class Target1 implements I1 {

        @Transactional
        public void t1(){}


        @Override
        public void foo() {
            System.out.println("foo");
        }

        @Override
        public void bar() {
            System.err.println("bar");
        }
    }
}
