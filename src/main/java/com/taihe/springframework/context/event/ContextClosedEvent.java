package com.taihe.springframework.context.event;

import com.taihe.springframework.context.ApplicationContext;

/**
 * @author qinth
 * @since 2025/1/21 20:48
 **/
public class ContextClosedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
