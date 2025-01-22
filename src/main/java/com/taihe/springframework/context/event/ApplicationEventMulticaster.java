package com.taihe.springframework.context.event;

import com.taihe.springframework.context.ApplicationEvent;
import com.taihe.springframework.context.ApplicationListener;

/**
 * @author qinth
 * @since 2025/1/21 20:52
 **/
public interface ApplicationEventMulticaster {

    /**
     * add a listener to be notified of all event
     *
     * @param listener the object to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * remove
     *
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * multicast the given application event to appropriate listeners
     *
     * @param event to multicast
     */
    void multicastEvent(ApplicationEvent event);
}
