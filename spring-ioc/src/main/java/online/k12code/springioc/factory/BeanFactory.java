package online.k12code.springioc.factory;

import org.springframework.beans.BeansException;

/**
 * bean容器
 *
 * @author Carl
 * @since 1.0.0
 */
public interface BeanFactory {

    /**
     * 获取bean
     *
     * @param name 名称
     * @return
     * @throws BeansException bean不存在时
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据名称和类型查找bean
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
