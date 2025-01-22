package com.taihe.springframework.context;

import java.util.EventListener;

/**
 * @author qinth
 * @since 2025/1/21 20:53
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
