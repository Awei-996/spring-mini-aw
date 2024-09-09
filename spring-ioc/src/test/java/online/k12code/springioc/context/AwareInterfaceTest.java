package online.k12code.springioc.context;

import online.k12code.springioc.context.support.ClassPathXmlApplicationContext;
import online.k12code.springioc.factory.HiService;
import org.junit.jupiter.api.Test;

/**
 * @author Carl
 * @since 1.0.0
 */
public class AwareInterfaceTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HiService hi = applicationContext.getBean("Hi", HiService.class);
        System.err.println(hi.getBeanFactory());
        System.err.println(hi.getApplicationContext());
    }
}
