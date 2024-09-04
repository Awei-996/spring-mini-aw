package online.k12code.springioc.factory.support;

import online.k12code.springioc.factory.config.BeanDefinition;

/**
 * BeanDefinition 注册接口
 *
 * @author Carl
 * @since 1.0.0
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注入BeanDefinition
     *
     * @param beanName 名称
     * @param beanDefinition 实体
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
