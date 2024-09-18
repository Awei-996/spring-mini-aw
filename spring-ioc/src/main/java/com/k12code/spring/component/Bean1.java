package com.k12code.spring.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author Carl
 * @since 1.0.0
 */
public class Bean1 {

    private static final Logger log = LoggerFactory.getLogger(Bean1.class);

    private Bean2 bean2;

    @Autowired
    public void setBean2(Bean2 bean2) {
        log.info("@Autowired:{}",bean2);
        this.bean2 = bean2;
    }

    @Autowired
    private Bean3 bean3;

    @Resource
    public void setBean3(Bean3 bean3){
        log.info("@Resource:{}",bean3);
        this.bean3 = bean3;
    }

    private String home;

    @Autowired
    public void getHome(@Value("${JAVA_HOME}") String home){
        log.info("@Value:{}",home);
        this.home = home;
    }


    @PostConstruct
    private void init(){
        log.info("初始化方法");
    }

    @PreDestroy
    private void destroy(){
        log.info("销毁方法");
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", bean3=" + bean3 +
                ", home='" + home + '\'' +
                '}';
    }
}
