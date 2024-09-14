package com.k12code.spring.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Carl
 * @since 1.0.0
 */
@Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(MyBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("lifecycleComponent".equals(beanName)) {
            log.info("=====>实例化之前,这里返回的对象会替换原本的bean");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("lifecycleComponent".equals(beanName)) {
            log.info("=========>实例化之后,这里返回false会跳过依赖注入阶段");
//            return false;
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if ("lifecycleComponent".equals(beanName)) {
                log.info("==========>注入依赖执行,例如 @Autowired、@Value、@Resource");
        }
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("lifecycleComponent".equals(beanName)) {
            log.info("========>初始化之前,这里返回的对象会替换原本的bean，如@PostConstruct、@ConfigurationProperties");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("lifecycleComponent".equals(beanName)) {
            log.info("========>初始化之后,这里返回的对象会替换原本的bean，如代理增强");
        }
        return bean;
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if ("lifecycleComponent".equals(beanName)) {
            log.info("销毁方法之前，例如：方法上添加@PreDestroy");
        }
    }
}
