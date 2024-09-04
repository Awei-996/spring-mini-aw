package online.k12code.springioc.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carl
 * @since 1.0.0
 */
public class BeanFactory {

    private Map<String, Object> beanMap = new HashMap<>();

    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

    public void registerBean(String beanName, Object bean) {
        beanMap.put(beanName, bean);
    }
}
