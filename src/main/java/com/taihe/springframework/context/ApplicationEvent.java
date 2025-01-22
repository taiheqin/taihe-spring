package com.taihe.springframework.context;

import java.util.EventObject;

/**
 * @author qinth
 * @since 2025/1/21 20:44
 **/
public abstract class ApplicationEvent extends EventObject {


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
