package com.k12code.spring;

import com.k12code.spring.component.Bean1;
import com.k12code.spring.component.Bean2;
import com.k12code.spring.component.Bean3;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author Carl
 * @since 1.0.0
 */
public class BeanPostProcessor {

    public static void main(String[] args) {
    t1();
    }

    public static void t1(){
        // 这是一个干净的容器，里面没有那些后置的处理器
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        // 注册bean
        applicationContext.registerBean("bean1", Bean1.class);
        applicationContext.registerBean("bean2", Bean2.class);
        applicationContext.registerBean("bean3", Bean3.class);

        //========== 添加后置处理器 ============
            // 通过添加后置处理器我们发现@Resource注解要比@Autowired先执行，那是因为Common后置处理器的执行顺序大于Autowired common的值小
        // 用来解析@Value注解
        applicationContext.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // AutowiredAnnotationBeanPostProcessor这个后置处理是用来解析 @Autowire @Value这两个注解
        applicationContext.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        // CommonAnnotationBeanPostProcessor 这个后置处理可以解析 @Resource、@PostConstruct、@PreDestroy
        applicationContext.registerBean(CommonAnnotationBeanPostProcessor.class);


        // 初始化容器 执行beanFactory后处理器，添加bean后处理器，初始化所有单例
        applicationContext.refresh();

        // 销毁
        applicationContext.close();
    }
}
