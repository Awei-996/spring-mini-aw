package online.k12code.springioc.factory;

/**
 * 初始化bean
 * @author Carl
 * @since 1.0.0
 */
public interface InitializingBean {

    /**
     * 在之后设置属性
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
