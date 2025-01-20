package com.taihe.springframework.beans.factory;

/**
 * Marker superinterface indicating that a bean is eligible to be
 * notified by the Spring container of a particular framework object
 * through a callback-style method.  Actual method signature is
 * determined by individual sub_interfaces, but should typically
 * consist of just one void-returning method that accepts a single
 * argument.
 *
 * @author qinth
 * @since 2025/1/20 15:43
 **/
public interface Aware {
}
