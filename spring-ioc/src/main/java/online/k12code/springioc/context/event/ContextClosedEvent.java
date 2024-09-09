package online.k12code.springioc.context.event;

/**
 * @author Carl
 * @since 1.0.0
 */
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
