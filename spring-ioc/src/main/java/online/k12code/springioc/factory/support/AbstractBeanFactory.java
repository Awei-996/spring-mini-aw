package online.k12code.springioc.factory.support;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.factory.BeanFactory;
import online.k12code.springioc.factory.config.BeanDefinition;

/**
 * 抽象的bean工厂
 *
 * @author Carl
 * @since 1.0.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * 创建bean
     *
     * @param beanName       名称
     * @param beanDefinition bean信息
     * @return 实体
     * @throws BeansException 异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    /**
     * 获取bean的具体信息
     *
     * @param beanName 名称
     * @return 信息
     * @throws BeansException 异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    @Override
    public Object getBean(String name) throws BeansException {
        // 先从单例中获取bean
        Object singleton = getSingleton(name);

        if (singleton != null) {
            return singleton;
        }
        // 如果单例中没有bean，就获取对应的beanDefinition，来创建一个bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }
}
