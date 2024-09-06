package online.k12code.springioc.factory.support;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.core.io.Resource;
import online.k12code.springioc.core.io.ResourceLoader;


/**
 * 读取bean信息
 *
 * @author Carl
 * @since 1.0.0
 */
public interface BeanDefinitionReader {

    /**
     * 获取bean信息
     *
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 资源加载器
     * @return
     */
    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;
}
