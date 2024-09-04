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
}
