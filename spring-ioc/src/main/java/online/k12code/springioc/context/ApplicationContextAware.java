package online.k12code.springioc.context;

import online.k12code.springioc.BeansException;
import online.k12code.springioc.factory.Aware;

/**
 * 实现该接口，能感知所属ApplicationContext
 *
 * @author Carl
 * @since 1.0.0
 */
public interface ApplicationContextAware extends Aware {
    /**
     * @param applicationContext
     * @throws BeansException
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
