package com.k12code.spring.component;

import com.k12code.spring.event.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author Carl
 * @since 1.0.0
 */
@Component
public class C3 {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void c3() {
        System.err.println("发送事件");
        publisher.publishEvent(new UserRegisterEvent(this, "carl"));
    }

}
