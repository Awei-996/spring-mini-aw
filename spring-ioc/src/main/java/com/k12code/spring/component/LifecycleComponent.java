package com.k12code.spring.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Carl
 * @since 1.0.0
 */
@Component
public class LifecycleComponent {

    private static final Logger log = LoggerFactory.getLogger(LifecycleComponent.class);

    public LifecycleComponent() {
        log.info("构造方法");
    }

    /**
     *  Autowired 是可以在普通方法上的，当我们方法的参数是一个bean的时候会自动加载或者使用@Value获取环境变量参数或者配置文件也会生效
     * @param javaHome
     */
    @Autowired
    public void a1(@Value("${JAVA_HOME}") String javaHome) {
        log.info("依赖注入：{}",javaHome);
    }

    @PostConstruct
    public void init1(){
        log.info("初始化");
    }

    @PreDestroy
    public void destroy1(){
        log.info("销毁方法");
    }
}
