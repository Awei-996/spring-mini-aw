package online.k12code.springioc.factory.support;

import online.k12code.springioc.BeansException;
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
     * @param beanName       名称
     * @param beanDefinition 实体
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);


    /**
     * 根据名称查找BeanDefinition
     * @param beanName 名称
     * @return BeanDefinition
     * @throws BeansException 异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 是否包含指定名称的BeanDefinition
     * @param beanName 名称
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回定义的所有bean名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
