package online.k12code.springioc.factory;

/**
 * @author Carl
 * @since 1.0.0
 */
public interface FactoryBean<T> {
    /**
     * 获取bean
     *
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 是不是单例
     *
     * @return
     */
    boolean isSingleton();
}
