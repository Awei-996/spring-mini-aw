package com.k12code.spring.springProxy;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * @author Carl
 * @since 1.0.0
 */
public class AnnotationPointcutTest {
    public static void main(String[] args) throws NoSuchMethodException {
        StaticMethodMatcherPointcut staticMethodMatcherPointcut = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                // 检查方法上是否有这个注解
                MergedAnnotations mergedAnnotations = MergedAnnotations.from(method);
                if (mergedAnnotations.isPresent(Transactional.class)) {
                    return true;
                }
                // 检查类上是否加这个注解 假如我们要检查这个类的父类或者他的接口上是否添加了这个注解，需要调整这个搜索的策略TYPE_HIERARCHY 改为层次结构
                mergedAnnotations = MergedAnnotations.from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                if (mergedAnnotations.isPresent(Transactional.class)) {
                    return true;
                }
                return false;
            }
        };

        System.out.println(staticMethodMatcherPointcut.matches(Target1.class.getMethod("foo"), Target1.class));
        System.out.println(staticMethodMatcherPointcut.matches(Target2.class.getMethod("foo"), Target2.class));
        System.out.println(staticMethodMatcherPointcut.matches(Target3.class.getMethod("foo"), Target3.class));

    }

    @Transactional
    interface I1 {
        void foo();

        void bar();
    }

    static class Target1 {
        @Transactional
        public void foo() {
        };
    }

    // 类上添加注解，他的每个方法都会有这个注解
    @Transactional
    static class Target2 {
        public void foo() {
        }

        ;
    }
    // 如果实现的这个接口，添加了注解，那么他的每个方法都会有
    static class Target3 implements I1 {

        @Override
        public void foo() {

        }

        @Override
        public void bar() {

        }
    }

}
