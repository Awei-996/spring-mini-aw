package online.k12code.springioc.context;

import online.k12code.springioc.bean.Car;
import online.k12code.springioc.bean.Person;
import online.k12code.springioc.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Carl
 * @since 1.0.0
 */
public class ApplicationContextTest {
    @Test
    public void testApplicationContext() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        //name属性在CustomBeanFactoryPostProcessor中被修改为aw
        assertThat(person.getName()).isEqualTo("aw");

        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);
        //brand属性在CustomerBeanPostProcessor中被修改为mini
        assertThat(car.getBrand()).isEqualTo("mini");
    }
}
