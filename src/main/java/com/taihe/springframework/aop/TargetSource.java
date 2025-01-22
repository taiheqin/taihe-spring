package com.taihe.springframework.aop;

import java.util.Objects;

/**
 * @author qinth
 * @since 2025/1/22 16:42
 **/
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * Return the type of targets returned by this TargetSource.
     * Can return null, although certain usages of a TargetSource might just work with a predetermined target class.
     *
     * @return the type of targets returned by this <code>TargetSource</code>
     */
    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    /**
     * Return a target instance. Invoked immediately before the
     * AOP framework calls the "target" of an AOP method invocation.
     *
     * @return the target object, which contains the joinPoint
     */
    public Object getTarget() {
        return this.target;
    }
}
