package online.k12code.springioc.context.event;

import online.k12code.springioc.context.ApplicationContext;
import online.k12code.springioc.context.ApplicationEvent;

/**
 * @author Carl
 * @since 1.0.0
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }
    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
