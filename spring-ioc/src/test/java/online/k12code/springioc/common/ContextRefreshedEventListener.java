package online.k12code.springioc.common;

import online.k12code.springioc.context.ApplicationListener;
import online.k12code.springioc.context.event.ContextRefreshedEvent;

/**
 * @author Carl
 * @since 1.0.0
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.err.println(this.getClass().getName());
    }
}
