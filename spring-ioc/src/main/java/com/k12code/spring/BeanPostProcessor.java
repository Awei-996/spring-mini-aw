package com.k12code.spring;

import com.k12code.spring.component.Bean1;
import com.k12code.spring.component.Bean2;
import com.k12code.spring.component.Bean3;
import com.k12code.spring.component.Properties;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Carl
 * @since 1.0.0
 */
public class BeanPostProcessor {

    public static void main(String[] args) throws Throwable {
//    t1();
        t2();
    }

    public static void t1(){
        // 这是一个干净的容器，里面没有那些后置的处理器
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        // 注册bean
        applicationContext.registerBean("bean1", Bean1.class);
        applicationContext.registerBean("bean2", Bean2.class);
        applicationContext.registerBean("bean3", Bean3.class);
        applicationContext.registerBean("properties", Properties.class);

        //========== 添加后置处理器 ============
            // 通过添加后置处理器我们发现@Resource注解要比@Autowired先执行，那是因为Common后置处理器的执行顺序大于Autowired common的值小
        // 用来解析@Value注解
        applicationContext.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // AutowiredAnnotationBeanPostProcessor这个后置处理是用来解析 @Autowire @Value这两个注解
        applicationContext.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        // CommonAnnotationBeanPostProcessor 这个后置处理可以解析 @Resource、@PostConstruct、@PreDestroy
        applicationContext.registerBean(CommonAnnotationBeanPostProcessor.class);

        // 添加Properties后置处理器
        ConfigurationPropertiesBindingPostProcessor.register(applicationContext.getDefaultListableBeanFactory());

        // 初始化容器 执行beanFactory后处理器，添加bean后处理器，初始化所有单例
        applicationContext.refresh();

        Properties properties = (Properties) applicationContext.getBean("properties");
        System.err.println(properties.getVersion());

        // 销毁
        applicationContext.close();
    }

    public static void t2() throws Throwable {
        // 详细解释 AutowiredAnnotationBeanPostProcessor后置处理器

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // registerSingleton 这个方法也可以注册bean，只不过他没有创建过程，依赖注入，初始化
        beanFactory.registerSingleton("bean2",new Bean2());
        beanFactory.registerSingleton("bean3",new Bean3());
        // @Value解析器
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

        // 1.查找那些属性、方法加了 @Autowired，这称之为InjectionMetadata
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setBeanFactory(beanFactory);

        Bean1 bean1 = new Bean1();
        // 执行依赖注入，实质是执行@Autowire @Value注解
//        beanPostProcessor.postProcessProperties(null,bean1,"bean1");
//        System.err.println(bean1);

        Method findAutowiringMetadata = AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
        findAutowiringMetadata.setAccessible(true);
        InjectionMetadata injectionMetadata = (InjectionMetadata) findAutowiringMetadata.invoke(beanPostProcessor, "bean1", Bean1.class, null);
        System.err.println(injectionMetadata);

        // 2.调用InjectionMetadata 来进行依赖注入，注入时按类型查找值
        injectionMetadata.inject(bean1,"bean1",null);
        System.err.println(bean1);
        // 3.按类型查找
        Field bean3 = Bean1.class.getDeclaredField("bean3");
        DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(bean3, false);
        Object o = beanFactory.doResolveDependency(dependencyDescriptor, null, null, null);
        System.err.println(o);
    }
}
