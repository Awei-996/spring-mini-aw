package online.k12code.springioc.context;

import java.util.EventListener;

/**
 * @author Carl
 * @since 1.0.0
 */
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    /**
     * 监听这个事件
     * @param event
     */
    void onApplicationEvent(E event);
}
