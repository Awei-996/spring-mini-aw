package online.k12code.springioc.context;

import online.k12code.springioc.bean.Car;
import online.k12code.springioc.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;

/**
 * @author Carl
 * @since 1.0.0
 */
public class FactoryBeanTest {

    @Test
    public void testFactoryBean() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = applicationContext.getBean("car", Car.class);
        System.err.println(car.getBrand());
    }
}
