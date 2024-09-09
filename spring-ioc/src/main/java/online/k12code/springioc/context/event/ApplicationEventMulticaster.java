package online.k12code.springioc.context.event;

import online.k12code.springioc.context.ApplicationEvent;
import online.k12code.springioc.context.ApplicationListener;

/**
 * @author Carl
 * @since 1.0.0
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
