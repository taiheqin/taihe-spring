package com.taihe.springframework.beans;

/**
 * @author qinth
 * @since 2024/7/8 16:48
 **/
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
