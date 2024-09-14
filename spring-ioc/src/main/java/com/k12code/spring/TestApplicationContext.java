package com.k12code.spring;


import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;


/**
 * @author Carl
 * @since 1.0.0
 */
public class TestApplicationContext {
    public static void main(String[] args) {
//        classXml();
//        fileXml();
// ========== xml注入实现的原理 ==========
//        classFileXml();
// ======== 使用@Configuration来实现bean的注入 =========
//        annotationConfig();
// ======== web来实现bean的注入 =========
        webServlet();
    }

    public static void classFileXml() {
        // 首先还是创建一个容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 将容器放入beanDefinition读取中
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        // 加载具体的资源
        xmlBeanDefinitionReader.loadBeanDefinitions("as.xml");

        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.err.println(beanDefinitionName);
        }
    }

    public static void classXml() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("as.xml");
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.err.println(beanDefinitionName);
        }
    }

    public static void fileXml() {
        // 如果找不到文件，可能是启动器中的工作目录不对，不是当前模块
        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("spring-ioc/src/main/resources/as.xml");
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.err.println(beanDefinitionName);
        }
    }

    public static void annotationConfig() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ac12.class);
        // 打印的时候我们可以看到，他会默认的已经给我们添加上了一些后置处理器
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.err.println(beanDefinitionName);
        }

    }

    public static void webServlet() {
        AnnotationConfigServletWebServerApplicationContext applicationContext = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

    }

}

@Configuration
class Ac12 {

    @Bean
    public Ac2 ac2() {
        return new Ac2();
    }

    @Bean
    public Ac1 ac1(Ac2 ac2) {
        Ac1 ac1 = new Ac1();
        ac1.setAc2(ac2);
        return ac1;
    }
}


class Ac1 {
    private Ac2 ac2;

    public Ac2 getAc2() {
        return ac2;
    }

    public void setAc2(Ac2 ac2) {
        this.ac2 = ac2;
    }
}

class Ac2 {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@Configuration
class WebConfig {
    /**
     * 注入一个tomcat容器
     *
     * @return
     */
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    /**
     * 注入Dispatcher
     *
     * @return
     */
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    /**
     * 注册访问路径
     *
     * @param dispatcherServlet
     * @return
     */
    @Primary
    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet) {
        return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
    }

    /**
     * 手动注入Controller，/会被解析成路径
     *
     * @return
     */
    @Bean("/hello")
    public Controller controller1() {
        return (request, response) -> {
            response.getWriter().print("hello");
            return null;
        };
    }
}