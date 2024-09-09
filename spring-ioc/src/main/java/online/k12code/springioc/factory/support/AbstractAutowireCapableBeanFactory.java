package online.k12code.springioc.factory.support;

import cn.hutool.core.util.ClassUtil;
import online.k12code.springioc.BeansException;
import online.k12code.springioc.PropertyValue;
import online.k12code.springioc.context.ApplicationContextAware;
import online.k12code.springioc.factory.BeanFactory;
import online.k12code.springioc.factory.BeanFactoryAware;
import online.k12code.springioc.factory.DisposableBean;
import online.k12code.springioc.factory.InitializingBean;
import online.k12code.springioc.factory.config.AutowireCapableBeanFactory;
import online.k12code.springioc.factory.config.BeanDefinition;
import online.k12code.springioc.factory.config.BeanPostProcessor;
import online.k12code.springioc.factory.config.BeanReference;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Carl
 * @since 1.0.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

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
            // 执行bean的初始化方法和BeanPostProcessor的前置和后置方法
            initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 注册有销毁方法的bean
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
        // 将新创建bean添加单例中
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 注册有销毁方法的bean，即bean继承DisposableBean或有自定义的销毁方法
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StringUtils.hasText(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
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
                declaredField.set(bean, value);
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

    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {

        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

        // 指定BeanPostProcessor的前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Throwable ex) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", ex);
        }

        //执行BeanPostProcessor的后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }


    /**
     * 执行bean的初始化方法
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @throws Throwable
     */
    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Throwable {
        if (bean instanceof InitializingBean) {
            ((InitializingBean)bean).afterPropertiesSet();
        }
        String initMethodName = beanDefinition.getInitMethodName();
        if (StringUtils.hasText(initMethodName) && !(bean instanceof InitializingBean && "afterPropertiesSet".equals(initMethodName))) {
            Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
            if (initMethod == null) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }
}
