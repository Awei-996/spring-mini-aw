package com.k12code.spring.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Carl
 * @since 1.0.0
 */
@Configuration
public class MyConfig {

    private static final Logger log = LoggerFactory.getLogger(MyConfig.class);

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        log.info("注入 Application Context");
    }

    @PostConstruct
    public void init(){
        log.info("初始化：init");
    }

    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor(){
        return beanFactory -> log.info("=================>>>>>>>注册：beanFactoryPostProcessor");
    }

}
