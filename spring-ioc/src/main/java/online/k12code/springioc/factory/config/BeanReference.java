package online.k12code.springioc.factory.config;

/**
 * 一个bean对另一个bean的引用
 *
 * @author Carl
 * @since 1.0.0
 */
public class BeanReference {
    private final String beanName;


    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
