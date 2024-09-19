package com.k12code.spring.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 需要实现BeanFactoryPostProcessor后置处理接口
 * @author Carl
 * @since 1.0.0
 */
public class ComponentBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try {
            // 先判断当前类有没有ComponentScan 注解
            ComponentScan annotation = AnnotationUtils.findAnnotation(BeanScan.class, ComponentScan.class);
            // 用于生成bean的名字
            AnnotationBeanNameGenerator annotationBeanNameGenerator = new AnnotationBeanNameGenerator();
            if (annotation != null) {
                for (String s : annotation.basePackages()) {
                    // 使用通配符对包名封装
                    String path = "classpath*:" + s.replace(".", "/") + "/**/*.class";
                    // 获取对应的资源 PathMatchingResourcePatternResolver 是ResourceLoader的实现者，所以他也可以读取资源
                    PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
                    Resource[] resources = pathMatchingResourcePatternResolver.getResources(path);
                    // 读取资源类的元信息
                    CachingMetadataReaderFactory cachingMetadataReaderFactory = new CachingMetadataReaderFactory();
                    for (Resource resource : resources) {
                        MetadataReader metadataReader = null;

                        metadataReader = cachingMetadataReaderFactory.getMetadataReader(resource);

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
                            if (configurableListableBeanFactory instanceof DefaultListableBeanFactory) {
                                DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;
                                // 生成bean的名字
                                String beanName = annotationBeanNameGenerator.generateBeanName(beanDefinition, defaultListableBeanFactory);
                                // 注册beanDefinition
                                defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinition);
                            }

                        }
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
