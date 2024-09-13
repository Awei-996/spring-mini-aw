package com.k12code.spring.component;

import com.k12code.spring.event.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Carl
 * @since 1.0.0
 */
@Component
public class C2 {

    /**
     * @EventListener 用于监听事件，参数必须是和发送的是一个事件
     *
     * @param registerEvent
     */
    @EventListener
    public void c2(UserRegisterEvent registerEvent) {
        System.err.println(registerEvent.name + ":发短信验证");
    }
}
