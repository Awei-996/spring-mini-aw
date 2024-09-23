package com.k12code.spring.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Carl
 * @since 1.0.0
 */
public class MyBean implements BeanNameAware, ApplicationContextAware, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(MyBean.class);

    /**
     *  重写BeanNameAware的方法，可以获取当前bean的名称
     */
    @Override
    public void setBeanName(String name) {
        log.info("setBeanName:{}",name);
    }


    /**
     * 重写ApplicationContext的方法，允许Bean实现类获取对Spring应用上下文（ApplicationContext）的引用。
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("设置ApplicationContext:{}",applicationContext);
    }

    /**
     * 重写InitializingBean的方法用于在Bean初始化完成后执行特定的逻辑。
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("初始化设置属性:");
    }

}
