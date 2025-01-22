package com.taihe.springframework.context.event;

import com.taihe.springframework.context.ApplicationContext;
import com.taihe.springframework.context.ApplicationEvent;

/**
 * @author qinth
 * @since 2025/1/21 20:46
 **/
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
