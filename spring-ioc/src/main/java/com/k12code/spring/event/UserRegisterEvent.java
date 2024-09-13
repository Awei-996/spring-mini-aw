package com.k12code.spring.event;


import org.springframework.context.ApplicationEvent;

/**
 * 定义事件，需要继承ApplicationEvent
 * @author Carl
 * @since 1.0.0
 */
public class UserRegisterEvent extends ApplicationEvent {

    public final String name;


    /**
     * source 事件源，就是谁发的事件
     * @param source
     */
    public UserRegisterEvent(Object source,String name) {
        super(source);
        this.name = name;
    }
}
