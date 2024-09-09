package online.k12code.springioc.common;

import online.k12code.springioc.context.ApplicationListener;
import online.k12code.springioc.context.event.ContextClosedEvent;

/**
 * @author Carl
 * @since 1.0.0
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.err.println(this.getClass().getName());
    }
}
