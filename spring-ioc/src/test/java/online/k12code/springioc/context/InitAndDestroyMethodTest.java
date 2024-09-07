package online.k12code.springioc.context;

import online.k12code.springioc.bean.Car;
import online.k12code.springioc.bean.Person;
import online.k12code.springioc.bean.Person2;
import online.k12code.springioc.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;

/**
 * @author Carl
 * @since 1.0.0
 */
public class InitAndDestroyMethodTest {
    @Test
    public void testInitAndDestroyMethod() throws InterruptedException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");

//        classPathXmlApplicationContext.registerShutdownHook();
        classPathXmlApplicationContext.close();
        Person2 person = classPathXmlApplicationContext.getBean("person2", Person2.class);
        System.out.println(person);
    }
}
