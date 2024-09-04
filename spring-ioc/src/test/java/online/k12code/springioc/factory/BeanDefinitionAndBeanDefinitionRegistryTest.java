package online.k12code.springioc.factory;

import online.k12code.springioc.factory.config.BeanDefinition;
import online.k12code.springioc.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

/**
 * @author Carl
 * @since 1.0.0
 */
public class BeanDefinitionAndBeanDefinitionRegistryTest {
    @Test
    public void testBeanFactory() {
        // 创建bean工厂
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        // 注册beanDefinition后才能实例化
        defaultListableBeanFactory.registerBeanDefinition("helloService",beanDefinition);
        // 获取bean具体的实例化，
        HelloService helloService = (HelloService) defaultListableBeanFactory.getBean("helloService");
        HelloService helloService2 = (HelloService) defaultListableBeanFactory.getBean("helloService");
        // 我们可以看到这两个bean是一个，是因为单例的保证
        System.err.println(helloService);
        System.err.println(helloService2);
        System.out.println(helloService.sayHello());
    }
}
