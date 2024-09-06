package online.k12code.springioc.factory.support;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.PropertyValue;
import online.k12code.springioc.factory.config.BeanDefinition;
import online.k12code.springioc.factory.config.BeanReference;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;

/**
 * @author Carl
 * @since 1.0.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();


    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            // 获取实例化
            bean = createBeanInstance(beanDefinition);
            // 为bean填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 将新创建bean添加单例中
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 使用策略实例化
     *
     * @param beanDefinition 属性
     * @return bean
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    /**
     * bean填充属性
     *
     * @param beanName       名称
     * @param bean           bean
     * @param beanDefinition beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {

            Object value = propertyValue.getValue();
            String name = propertyValue.getName();

            if (value instanceof BeanReference) {
                // beanA依赖beanB，先实例化beanB
                value = getBean(((BeanReference) value).getBeanName());
            }

            // 通过反射设置属性
            try {
                // 确保这个bean有这个属性
                Field declaredField = bean.getClass().getDeclaredField(name);
                // 绕过访问修饰符限制，private修饰的不能外部访问
                declaredField.setAccessible(true);
                declaredField.set(bean,value);
            } catch (Exception e) {
                throw new BeansException("Error setting property values for bean: " + beanName, e);
            }
        }
    }


    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
