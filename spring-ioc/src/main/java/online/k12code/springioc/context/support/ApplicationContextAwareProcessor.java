package online.k12code.springioc.context.support;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.context.ApplicationContext;
import online.k12code.springioc.context.ApplicationContextAware;
import online.k12code.springioc.factory.config.BeanPostProcessor;

/**
 * @author Carl
 * @since 1.0.0
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
