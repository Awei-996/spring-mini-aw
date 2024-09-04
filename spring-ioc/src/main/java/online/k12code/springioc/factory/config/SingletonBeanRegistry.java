package online.k12code.springioc.factory.config;

/**
 * 单例注册表
 *
 * @author Carl
 * @since 1.0.0
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例bean
     *
     * @param beanName 名称
     * @return 实体
     */
    Object getSingleton(String beanName);
}
