package online.k12code.springioc.factory.support;

import cn.hutool.core.util.ClassUtil;
import online.k12code.springioc.BeansException;
import online.k12code.springioc.factory.DisposableBean;
import online.k12code.springioc.factory.config.BeanDefinition;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author Carl
 * @since 1.0.0
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        // 避免同时继承自DisposableBean, 且自定义方法与DisposableBean方法同名，销毁方法执行两次的情况
        if (StringUtils.hasText(destroyMethodName) && !(bean instanceof DisposableBean && "destory".equals(this.destroyMethodName))) {
            // 执行自定义方法
            Method destoryMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if (destoryMethod == null) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destoryMethod.invoke(bean);
        }
    }
}
