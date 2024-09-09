package online.k12code.springioc.factory;

/**
 * 实现该接口,能感知所属BeanFactory
 * @author Carl
 * @since 1.0.0
 */
public interface BeanFactoryAware extends Aware {
    /**
     * 设置
     * @param beanFactory
     */
    void setBeanFactory(BeanFactory beanFactory);
}
