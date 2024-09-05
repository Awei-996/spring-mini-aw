package online.k12code.springioc.factory;

import online.k12code.springioc.PropertyValue;
import online.k12code.springioc.PropertyValues;
import online.k12code.springioc.bean.Person;
import online.k12code.springioc.factory.config.BeanDefinition;
import online.k12code.springioc.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

/**
 * Bean填充属性
 * @author Carl
 * @since 1.0.0
 */
public class PopulateBeanWithPropertyValuesTest {

    @Test
    public void testPopulateBeanWithPropertyValuesTest(){
        // 创建beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 填充属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","carl"));
        propertyValues.addPropertyValue(new PropertyValue("age",18));

        // 创建bean的信息
        BeanDefinition beanDefinition = new BeanDefinition(Person.class,propertyValues);
        // 注册BeanDefinition,并不是bean
        beanFactory.registerBeanDefinition("Person",beanDefinition);

        // 获取bean，如果第一次获取 这个时候才是真正的去创建bean，并为bean填充属性，然后放到集合中，下次在获取就是直接获取了
        Person person = (Person) beanFactory.getBean("Person");

        System.err.println(person.getName());
        System.err.println(person.getAge());
    }
}
