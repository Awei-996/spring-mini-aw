package online.k12code.springioc.context;

import online.k12code.springioc.BeansException;

/**
 *
 * @author Carl
 * @since 1.0.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
