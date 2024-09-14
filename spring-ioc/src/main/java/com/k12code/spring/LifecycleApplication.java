package com.k12code.spring;

import com.k12code.spring.component.LifecycleComponent;
import com.k12code.spring.component.MyBeanPostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;

/**
 * @author Carl
 * @since 1.0.0
 */
public class LifecycleApplication {
    public static void main(String[] args) {
        //====================== 第一种写法 ===================
        // 创建beanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册beanDefinition
        AbstractBeanDefinition singleton = BeanDefinitionBuilder.genericBeanDefinition(LifecycleComponent.class)
                .setScope("singleton").getBeanDefinition();
        AbstractBeanDefinition singleton2 = BeanDefinitionBuilder.genericBeanDefinition(MyBeanPostProcessor.class)
                .setScope("singleton").getBeanDefinition();

        beanFactory.registerBeanDefinition("lifecycleComponent",singleton);
        beanFactory.registerBeanDefinition("myBeanPostProcessor",singleton2);

        // 注册后置处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        // 使用BeanFactory后置处理器
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });
        // 添加bean后置处理器
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);
        // 提前实例化初始化
        beanFactory.preInstantiateSingletons();

        Object config = beanFactory.getBean("lifecycleComponent");
        System.err.println(config);
        Object myBean= beanFactory.getBean("myBeanPostProcessor");
        System.err.println(myBean);
        // 销毁方法
        beanFactory.destroySingletons();

        // ============= 第二种方法 =============
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(LifecycleComponent.class, MyBeanPostProcessor.class);
//        applicationContext.close();
    }
}
