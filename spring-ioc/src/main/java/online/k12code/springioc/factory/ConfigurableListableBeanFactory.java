package online.k12code.springioc.factory;


import online.k12code.springioc.BeansException;
import online.k12code.springioc.factory.config.AutowireCapableBeanFactory;
import online.k12code.springioc.factory.config.BeanDefinition;
import online.k12code.springioc.factory.config.BeanPostProcessor;
import online.k12code.springioc.factory.config.ConfigurableBeanFactory;

/**
 * @author Carl
 * @since 1.0.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据名称查找BeanDefinition
     *
     * @param beanName
     * @return
     * @throws
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化所有单例实例
     *
     * @throws org.springframework.beans.BeansException
     */
    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
