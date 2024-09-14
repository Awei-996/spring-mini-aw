package com.k12code.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Carl
 * @since 1.0.0
 */
public class TestBeanFactory {

    public static void main(String[] args) {
        // beanFactory的实现类容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // bean 的定义（class，scope,初始化，销毁）
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(BeanConfig.class)
                .setScope("singleton")
                .addPropertyValue("name", "xx")
                .getBeanDefinition();

        beanFactory.registerBeanDefinition("config", beanDefinition);
/**

 // 我们可以看到他只注册了config类的beanDefinition，它里面的bean A B 并没有注册
 for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
 System.out.println(beanDefinitionName);
 }
 BeanConfig config = (BeanConfig) beanFactory.getBean("config");
 System.err.println(config.name);
 *
 */
        // 添加一些常用的后置处理器,默认会添加上这几个的后置处理器
//        org.springframework.context.annotation.internalConfigurationAnnotationProcessor
//        org.springframework.context.annotation.internalAutowiredAnnotationProcessor
//        org.springframework.context.annotation.internalCommonAnnotationProcessor
//        org.springframework.context.event.internalEventListenerProcessor
//        org.springframework.context.event.internalEventListenerFactory
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        // 执行BeanFactoryPostProcessor后置处理器,他是在实例化之前执行的，可以修改注册的BeanDefinition,实质是对bean定义的补充
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().stream().forEach(beanFactoryPostProcessor -> {
            // 通过打印我们可以看到只有 ConfigurationClassPostProcessor、EventListenerMethodProcessor这两个是beanFactory的后置处理器
            // ConfigurationClassPostProcessor 他会解析类里面@Bean 注解
//            System.err.println(beanFactoryPostProcessor);
            // 执行后置处理器
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });

        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        // 现在在B类里面注入了一个A,但是他并没有获取到，此时我们需要加BeanPostProcessor后置处理器，该处理器是在实例化之后，初始化之前执行的
        // 因为我们刚才在BeanDefinition中了多个，其中AutowiredAnnotationProcessor这个就是解析@Autowired的BeanPostProcessor后置处理器
        // 添加order比较顺序加载后置处理器
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().stream().sorted(Objects.requireNonNull(beanFactory.getDependencyComparator())).forEach(beanFactory::addBeanPostProcessor);
        // 我们可以在使用之前把所有的bean都初始化了
        beanFactory.preInstantiateSingletons();
        System.err.println(">>>>>>>>>>>>>>>>>");
        // 我们只有真正在过去bean的时候他才会进行实例化、初始化，原来之后把他放在beanDefinition中
        B bean = beanFactory.getBean(B.class);
        System.err.println(bean.getName());
        System.err.println(bean.getA());

        // 测试@Autowired @Resource谁的优先级高
        System.err.println(beanFactory.getBean(A.class).getInter());
    }

}

@Configuration
class BeanConfig {

    public String name;

    @Bean
    public A aa() {
        return new A();
    }


    @Bean
    public B bb() {
        return new B();
    }

    @Bean
    public C c(){
        return new C();
    }

    @Bean
    public D d(){
        return new D();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class A {
    public final String name = "aw";

    @Resource(name = "d")
    @Autowired
    private Inter c;

    public Inter getInter() {
        return c;
    }
}

class B {
    public final String name = "carl";

    @Autowired
    private A a;

    public A getA() {
        return a;
    }

    public String getName() {
        return name;
    }
}

interface Inter {
}

class C implements Inter {
}

class D implements Inter {
}