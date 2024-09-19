package com.k12code.spring;

import com.k12code.spring.component.BeanScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
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


        ComponentScan annotation = AnnotationUtils.findAnnotation(BeanScan.class, ComponentScan.class);

        if (annotation != null) {
            for (String s : annotation.basePackages()) {

                String path = "classpath*:" + s.replace(".", "/") + "/**/*.class";
                Resource[] resources = applicationContext.getResources(path);
                CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();

                for (Resource resource : resources) {
//                    System.err.println("resource:" + resource);
                    MetadataReader metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);
                    System.out.println("类名："+metadataReader.getClassMetadata().getClassName());
                    System.out.println("是否添加 @Component注解："+metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName()));
                    System.out.println("是否添加 @Component注解的派生类："+metadataReader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName()));
                }
            }

        }

        applicationContext.refresh();

        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.err.println(beanDefinitionName);
        }

        applicationContext.close();
    }
}

