package com.taihe.springframework.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author qinth
 * @since 2024/7/9 11:05
 **/
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        return this.propertyValueList.stream()
                .filter(obj -> Objects.equals(obj.getName(), propertyName))
                .findFirst()
                .orElse(null);
    }
}
