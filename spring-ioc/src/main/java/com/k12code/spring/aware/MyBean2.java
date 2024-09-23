package com.k12code.spring.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author Carl
 * @since 1.0.0
 */
public class MyBean2 {

    private static final Logger log = LoggerFactory.getLogger(MyBean2.class);

    @Autowired
    public void getApplicationContext(ApplicationContext applicationContext) {
        log.info("获取applicationContext:{}",applicationContext);
    }

    @PostConstruct
    public void init(){
        log.info("初始化 bean");
    }

}
