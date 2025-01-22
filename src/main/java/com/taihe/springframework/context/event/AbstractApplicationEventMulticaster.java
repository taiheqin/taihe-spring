package com.taihe.springframework.context.event;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.BeanFactory;
import com.taihe.springframework.beans.factory.BeanFactoryAware;
import com.taihe.springframework.context.ApplicationEvent;
import com.taihe.springframework.context.ApplicationListener;
import com.taihe.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author qinth
 * @since 2025/1/21 20:59
 **/
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * get all support listeners
     *
     * @param event published event
     * @return collections of support listeners
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        // use different strategy to get class with CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }

        /**
         Determine whether the class or interface represented by this eventClassName object is the same as,
         or is a superclass or superinterface of, the class or interface represented by the specified event.getClass() parameter.
         isAssignableFrom is used to determine the relationship between subclasses and superclasses,
         or between an interface's implementation class and the interface itself.
         By default, the ultimate superclass of all classes is Object.
         If A.isAssignableFrom(B) returns true, it means that B can be cast to A, i.e., A can be derived from B.
         */
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
