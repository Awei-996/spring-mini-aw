package online.k12code.springioc.factory.config;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * @author Carl
 * @since 1.0.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

}
