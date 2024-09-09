package online.k12code.springioc.context;

import online.k12code.springioc.core.io.ResourceLoader;
import online.k12code.springioc.factory.HierarchicalBeanFactory;
import online.k12code.springioc.factory.ListableBeanFactory;

/**
 * 应用上下文
 * @author Carl
 * @since 1.0.0
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
