package online.k12code.springioc.context;

import online.k12code.springioc.BeansException;

/**
 * @author Carl
 * @since 1.0.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;


    /**
     * 关闭应用上下文
     */
    void close();

    /**
     * 像虚拟机注册一个钩子方法，在虚拟机关闭之前执行关闭容器等操作
     */
    void registerShutdownHook();
}
