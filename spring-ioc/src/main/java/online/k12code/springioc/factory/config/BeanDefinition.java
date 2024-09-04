package online.k12code.springioc.factory.config;

/**
 * 用于保存bean的信息，包括class类型、方法构造参数、是否为单例等，此处简化只包含class类型
 * @author Carl
 * @since 1.0.0
 */
public class BeanDefinition {

    private Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
