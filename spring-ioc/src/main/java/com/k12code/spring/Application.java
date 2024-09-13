package com.k12code.spring;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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

       getMessage();
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
}
