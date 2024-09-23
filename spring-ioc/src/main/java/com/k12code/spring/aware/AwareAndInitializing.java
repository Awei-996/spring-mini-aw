package com.k12code.spring.aware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author Carl
 * @since 1.0.0
 */
public class AwareAndInitializing {
    public static void main(String[] args) {
//        m1();
//        验证注解方法，需要添加后置处理器
//        m2();
//        @Autowired失效
//        m3();
//        @Autowired失效 解决办法
        m4();
    }

    public static void m1() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean("myBean", MyBean.class);
        applicationContext.refresh();
        applicationContext.close();
    }

    public static void m2() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();

        // 添加对应的后置处理器
        applicationContext.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        applicationContext.registerBean(CommonAnnotationBeanPostProcessor.class);

        applicationContext.registerBean("myBean2", MyBean2.class);


        applicationContext.refresh();
        applicationContext.close();
    }

    public static void m3() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(MyConfig.class);

        applicationContext.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        applicationContext.registerBean(CommonAnnotationBeanPostProcessor.class);
        applicationContext.registerBean(ConfigurationClassPostProcessor.class);


        applicationContext.refresh();

        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.err.println(beanDefinitionName);
        }
        applicationContext.close();
    }

    public static void m4() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(MyConfig2.class);

        applicationContext.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        applicationContext.registerBean(CommonAnnotationBeanPostProcessor.class);
        applicationContext.registerBean(ConfigurationClassPostProcessor.class);


        applicationContext.refresh();

        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.err.println(beanDefinitionName);
        }
        applicationContext.close();
    }
}
