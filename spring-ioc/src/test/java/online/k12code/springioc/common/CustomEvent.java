package online.k12code.springioc.common;

import online.k12code.springioc.context.ApplicationEvent;
import online.k12code.springioc.context.event.ApplicationContextEvent;

/**
 * @author Carl
 * @since 1.0.0
 */
public class CustomEvent extends ApplicationContextEvent {

    public CustomEvent(Object source) {
        super(source);
    }
}
