package online.k12code.springioc.common;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.PropertyValue;
import online.k12code.springioc.PropertyValues;
import online.k12code.springioc.factory.ConfigurableListableBeanFactory;
import online.k12code.springioc.factory.config.BeanDefinition;
import online.k12code.springioc.factory.config.BeanFactoryPostProcessor;
import online.k12code.springioc.factory.config.ConfigurableBeanFactory;


/**
 * @author Carl
 * @since 1.0.0
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition personBeanDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personBeanDefinition.getPropertyValues();
        // 修改name的值
        propertyValues.addPropertyValue(new PropertyValue("name", "aw"));
    }

}
