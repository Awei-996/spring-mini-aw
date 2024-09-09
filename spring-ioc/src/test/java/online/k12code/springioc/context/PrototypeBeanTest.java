package online.k12code.springioc.context;

import online.k12code.springioc.bean.Car;
import online.k12code.springioc.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;

/**
 * @author Carl
 * @since 1.0.0
 */
public class PrototypeBeanTest {
    @Test
    public void testPrototype() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
        Car car = applicationContext.getBean("car", Car.class);
        Car car2 = applicationContext.getBean("car", Car.class);
        System.err.println(car.hashCode());
        System.err.println(car2.hashCode());
        System.err.println(car.equals(car2));
    }
}
