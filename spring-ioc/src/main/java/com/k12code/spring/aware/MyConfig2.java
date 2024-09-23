package com.k12code.spring.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Carl
 * @since 1.0.0
 */
@Configuration
public class MyConfig2 implements ApplicationContextAware, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(MyConfig2.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("init");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("注入：applicationContext");
    }

    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor(){
        return beanFactory -> log.info("=================>>>>>>>注册：beanFactoryPostProcessor");
    }
}
