package online.k12code.springioc.context.event;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.context.ApplicationEvent;
import online.k12code.springioc.context.ApplicationListener;
import online.k12code.springioc.factory.BeanFactory;
import online.k12code.springioc.factory.BeanFactoryAware;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Carl
 * @since 1.0.0
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener){
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException{
        this.beanFactory = beanFactory;
    }
}
