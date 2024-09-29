package com.k12code.spring.springProxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Carl
 * @since 1.0.0
 */
public class AdvisorTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        @SuppressWarnings("resource")
        GenericApplicationContext applicationContext = new GenericApplicationContext();

        applicationContext.registerBean("MyAspect", MyAspect.class);
        applicationContext.registerBean("Config", Config.class);
        applicationContext.registerBean(ConfigurationClassPostProcessor.class);
        applicationContext.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);

        applicationContext.refresh();

        // 获取AnnotationAwareAspectJAutoProxyCreator实例
        AnnotationAwareAspectJAutoProxyCreator creator = applicationContext.getBean(AnnotationAwareAspectJAutoProxyCreator.class);

        // 使用反射获取findEligibleAdvisors方法，从AbstractAdvisorAutoProxyCreator类中获取
        Method findEligibleAdvisors = AbstractAdvisorAutoProxyCreator.class.getDeclaredMethod("findEligibleAdvisors", Class.class, String.class);
        // 设置方法可访问
        findEligibleAdvisors.setAccessible(true);

        // 调用findEligibleAdvisors方法
        List<Advisor> advisors = (List<Advisor>) findEligibleAdvisors.invoke(creator, Target1.class, "target1");

        // 打印结果
        for (Advisor advisor : advisors) {
            System.err.println(advisor);
        }

    }
}
