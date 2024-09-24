package com.k12code.spring.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Carl
 * @since 1.0.0
 */
@ComponentScan(basePackages = "com.k12code.spring.scope")
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.err.println(beanDefinitionName);
        }

        F1 bean = applicationContext.getBean(F1.class);
        System.out.println(bean.getS1());
        System.out.println(bean.getS1());
        System.out.println(bean.getS1());
        System.err.println("================");
        System.out.println(bean.getS2());
        System.out.println(bean.getS2());
        System.out.println(bean.getS2());
    }
}
