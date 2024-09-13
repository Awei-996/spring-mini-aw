package com.k12code.spring.component;

import com.k12code.spring.event.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Carl
 * @since 1.0.0
 */
@Component
public class C1 {

    @EventListener
    public void c2(UserRegisterEvent registerEvent) {
        System.err.println(registerEvent.name + ":发邮箱验证");
    }
}
