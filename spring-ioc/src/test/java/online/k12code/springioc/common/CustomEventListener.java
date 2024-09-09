package online.k12code.springioc.common;

import online.k12code.springioc.context.ApplicationListener;

/**
 * @author Carl
 * @since 1.0.0
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.err.println(this.getClass().getName());
    }
}
