package online.k12code.springioc.context;

/**
 * 事件发布接口
 * @author Carl
 * @since 1.0.0
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     * @param applicationEvent
     */
    void publishEvent(ApplicationEvent applicationEvent);
}
