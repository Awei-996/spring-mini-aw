package online.k12code.springioc.context.event;

/**
 * @author Carl
 * @since 1.0.0
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
