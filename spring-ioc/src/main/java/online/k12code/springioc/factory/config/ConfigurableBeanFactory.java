package online.k12code.springioc.factory.config;


import online.k12code.springioc.BeansException;
import online.k12code.springioc.factory.HierarchicalBeanFactory;


/**
 * @author Carl
 * @since 1.0.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
