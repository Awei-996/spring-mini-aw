package online.k12code.springioc.factory.config;


import online.k12code.springioc.factory.HierarchicalBeanFactory;


/**
 * @author Carl
 * @since 1.0.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * 添加
     *
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例bean
     */
    void destroySingletons();
}
