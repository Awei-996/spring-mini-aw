package com.k12code.spring;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

/**
 * @author Carl
 * @since 1.0.0
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {

//       getMessage();
        getResource();
    }

    /**
     *  反射从单例中获取bean
     * @param args
     * @throws Exception
     */
    private void getSingletonObjects(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        // 通过反获取注入在单例中的bean
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        singletonObjects.setAccessible(true);
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        map.entrySet().stream().filter(e -> e.getKey().startsWith("c")).forEach(System.err::println);
    }

    /**
     * 国际化
     */
    private static void getMessage(){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class);

        String h1 = applicationContext.getMessage("hi", null, Locale.CHINA);
        String h2 = applicationContext.getMessage("hi", null, Locale.SIMPLIFIED_CHINESE);
        String h3 = applicationContext.getMessage("hi", null, Locale.ENGLISH);
        String h4 = applicationContext.getMessage("hi", null,Locale.ROOT);

        System.err.println(h1);
        System.err.println(h2);
        System.err.println(h3);
        System.err.println(h4);
    }

    /**
     * 获取资源
     * @throws IOException
     */
    private static void getResource() throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class);
        // 在本项目的类路径下进行匹配
        Resource resource = applicationContext.getResource("classpath:application.properties");
        // 在本地磁盘进行匹配
        Resource resource1 = applicationContext.getResource("file:E:\\Spring\\spring-mini-aw\\spring-ioc\\src\\main\\resources\\a.txt");
        // 在jar包文件中匹配
        Resource[] resources = applicationContext.getResources("classpath*:META-INF/spring.factories");
        System.err.println(resources.getClass());
        System.err.println(resource1);
        for (Resource resource2 : resources) {
            System.err.println(resource2);
        }
    }
}
