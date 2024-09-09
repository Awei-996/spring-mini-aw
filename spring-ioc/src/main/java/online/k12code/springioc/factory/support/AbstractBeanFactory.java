package online.k12code.springioc.factory.support;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.factory.BeanFactory;
import online.k12code.springioc.factory.FactoryBean;
import online.k12code.springioc.factory.config.BeanDefinition;
import online.k12code.springioc.factory.config.BeanPostProcessor;
import online.k12code.springioc.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象的bean工厂
 *
 * @author Carl
 * @since 1.0.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

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
            // 如果FactoryBean,从FactoryBean#getObject中创建bean
            return getObjectForBeanInstance(singleton, name);
        }
        // 如果单例中没有bean，就获取对应的beanDefinition，来创建一个bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition);
        return getObjectForBeanInstance(bean,name);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        //有则覆盖
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return ((T) getBean(name));
    }

    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        Object object = beanInstance;
        if (beanInstance instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) beanInstance;
            try {
                if (factoryBean.isSingleton()) {
                    // singleton作用域bean，从缓存中获取
                    object = this.factoryBeanObjectCache.get(beanName);
                    if (object == null) {
                        object = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(beanName,object);
                    }
                } else {
                    //prototype 作用域bean，新建bean
                    object = factoryBean.getObject();
                }
            } catch (Exception ex) {
                throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", ex);
            }
        }
        return object;
    }
}
