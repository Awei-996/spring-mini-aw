package online.k12code.springioc.factory;

/**
 * 销毁bean
 *
 * @author Carl
 * @since 1.0.0
 */
public interface DisposableBean {

    /**
     * 销毁方法
     * @throws Exception
     */
    void destroy() throws Exception;
}
