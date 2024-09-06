package online.k12code.springioc.common;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.bean.Car;
import online.k12code.springioc.factory.config.BeanPostProcessor;

/**
 * @author Carl
 * @since 1.0.0
 */
public class CustomerBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("before");
        if ("car".equals(beanName)){
            ( (Car) bean).setBrand("mini");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("after");
        return bean;
    }
}
