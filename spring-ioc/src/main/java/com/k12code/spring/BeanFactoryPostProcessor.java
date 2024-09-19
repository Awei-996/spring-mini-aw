package com.k12code.spring;

import com.k12code.spring.component.BeanScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Carl
 * @since 1.0.0
 */
public class BeanFactoryPostProcessor {

    public static void main(String[] args) throws IOException {
//        t1();
        // @Component 实现过程
        t2();
    }

    public static void t1() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        // ConfigurationClassPostProcessor 这个是beanFactory后置处理器，用于解析 @ComponentScan、@Bean、@Import、@ImportResource
        applicationContext.registerBean(ConfigurationClassPostProcessor.class);
        applicationContext.registerBean(BeanScan.class);
        // MapperScannerConfigurer 用于扫描mapper组件的beanFactory后置处理器
        applicationContext.registerBean(MapperScannerConfigurer.class, bd -> {
            bd.getPropertyValues().addPropertyValue("basePackage", "com.k12code.spring.component.mapper");
        });

        applicationContext.refresh();

        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.err.println(beanDefinitionName);
        }

        applicationContext.close();
    }

    public static void t2() throws IOException {
        // 探究ConfigurationClassPostProcessor后置处理器实现的原理
        GenericApplicationContext applicationContext = new GenericApplicationContext();

        applicationContext.registerBean(BeanScan.class);
        // 先判断当前类有没有ComponentScan 注解
        ComponentScan annotation = AnnotationUtils.findAnnotation(BeanScan.class, ComponentScan.class);
        // 用于生成bean的名字
        AnnotationBeanNameGenerator annotationBeanNameGenerator = new AnnotationBeanNameGenerator();
        if (annotation != null) {
            for (String s : annotation.basePackages()) {
                // 使用通配符对包名封装
                String path = "classpath*:" + s.replace(".", "/") + "/**/*.class";
                // 获取对应的资源
                Resource[] resources = applicationContext.getResources(path);
                // 读取资源类的元信息
                CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
                for (Resource resource : resources) {
                    MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
//                    System.out.println("类名："+metadataReader.getClassMetadata().getClassName());
//                    System.out.println("是否添加 @Component注解："+metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName()));
//                    System.out.println("是否添加 @Component注解的派生类："+metadataReader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName()));
                    // 获取类名
                    String className = metadataReader.getClassMetadata().getClassName();
                    // 获取注解
                    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                    // 先判断他是不是有Component 或者是他的派生注解
                    if (annotationMetadata.hasAnnotation(Component.class.getName()) || annotationMetadata.hasMetaAnnotation(Component.class.getName())) {
                        // 创建beanDefinition
                        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(className).getBeanDefinition();
                        // 获取beanFactory
                        DefaultListableBeanFactory defaultListableBeanFactory = applicationContext.getDefaultListableBeanFactory();
                        // 生成bean的名字
                        String beanName = annotationBeanNameGenerator.generateBeanName(beanDefinition, defaultListableBeanFactory);
                        // 注册beanDefinition
                        defaultListableBeanFactory.registerBeanDefinition(beanName,beanDefinition);
                    }
                }
            }

        }

        applicationContext.refresh();

        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.err.println("注册的bean:"+beanDefinitionName);
        }

        applicationContext.close();
    }
}

