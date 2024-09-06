package online.k12code.springioc.factory.config;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.factory.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改BeanDefinition的属性值，在实例化之前
 * @author Carl
 * @since 1.0.0
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有BeanDefinition加载完后，但在bean实例化之前，提供修改BeanDefinition属性值的机制
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
