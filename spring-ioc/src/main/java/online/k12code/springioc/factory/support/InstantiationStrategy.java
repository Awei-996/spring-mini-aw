package online.k12code.springioc.factory.support;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.factory.config.BeanDefinition;

/**
 * Bean实例化策略
 *
 * @author Carl
 * @since 1.0.0
 */
public interface InstantiationStrategy {
    /**
     * 实例化策略
     *
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
