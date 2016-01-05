package org.sdi.core;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by guoyanli on 12/25/15.
 */
public class Property {
    /**
     * property type: class name
     */
    private String propertyType;
    /**
     * property name, can be used to get property type if propertyType is not given
     * required
     */
    private String propertyName;
    /**
     * value
     */
    private String propertyValue;
    /**
     * another bean
     */
    private String propertyRef;
    /**
     * default to be false
     */
    private final AtomicReference<Boolean> isAutowared = new AtomicReference<Boolean>(false);
    /**
     * autowared type
     */
    protected Autoware autowareType = Autoware.None;

    public void setAutowareType(Autoware autoware) {
        this.autowareType = autoware;
    }

    public Autoware getAutowareType() {
        if (getIsAutowared()) {
            return autowareType;
        } else {
            return Autoware.None;
        }
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyRef() {
        return propertyRef;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyRef(String propertyRef) {
        this.propertyRef = propertyRef;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Boolean getIsAutowared() {
        return isAutowared.get();
    }

    public void setIsAutowared(Boolean isAutowared) {
        Boolean oldIsAutowared = this.isAutowared.getAndSet(isAutowared);
        // assert oldIsAutoWared == isAutowared
    }

}
