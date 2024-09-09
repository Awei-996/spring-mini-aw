package online.k12code.springioc.factory.support;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.factory.DisposableBean;
import online.k12code.springioc.factory.config.SingletonBeanRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认单例bean的注册
 *
 * @author Carl
 * @since 1.0.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    private final Map<String, DisposableBean> disposables = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposables.put(beanName,bean);
    }

    public void destroySingletons(){
        ArrayList<String> beanNames = new ArrayList<>(disposables.keySet());

        for (String beanName : beanNames) {
            removeSingleton(beanName);
            DisposableBean disposableBean = disposables.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

    protected void removeSingleton(String beanName) {
        this.singletonObjects.remove(beanName);
    }
}
