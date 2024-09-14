package com.k12code.spring;



import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author Carl
 * @since 1.0.0
 */
public class TestApplicationContext {
    public static void main(String[] args) {
//        classXml();
//        fileXml();
// ========== xml注入实现的原理 ==========
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