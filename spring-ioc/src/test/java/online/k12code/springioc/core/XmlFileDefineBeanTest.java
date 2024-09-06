package online.k12code.springioc.core;

import online.k12code.springioc.bean.Car;
import online.k12code.springioc.bean.Person;
import online.k12code.springioc.factory.support.DefaultListableBeanFactory;
import online.k12code.springioc.factory.xml.XmlBeanDefinitionReader;
import org.junit.jupiter.api.Test;

/**
 * @author Carl
 * @since 1.0.0
 */
public class XmlFileDefineBeanTest {
    @Test
    public void testXmlFile() throws Exception {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.err.println(person);

        Car car = (Car) beanFactory.getBean("car");
        System.err.println(car);
    }
}
