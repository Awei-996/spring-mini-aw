package online.k12code.springioc.factory;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.context.ApplicationContext;
import online.k12code.springioc.context.ApplicationContextAware;

/**
 * @author Carl
 * @since 1.0.0
 */
public class HiService implements ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory=beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
