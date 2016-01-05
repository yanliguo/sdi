package org.sdi.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by guoyanli on 12/25/15.
 */
abstract public class BeanDefinition {
    public static final String ID_NAME = "id";
    public static final String CLASS_NAME = "class";
    public static final String CONSTRUCT_ARGS_NAME = "construct-args";
    public static final String LAZY_NAME = "isLazy";
    public static final String PROPERTIES_NAME = "properties";

    /**
     * required bean id
     */
    protected String id;
    /**
     * required class name
     */
    protected String className;
    /**
     * null or empty
     */
    protected List<ConstructArguments> constructArgs = new ArrayList<ConstructArguments>();
    // this bean is dependent on by these beans
    /**
     * this is dependent by bean ids
     */
    protected Set<String> dependentBy = new HashSet<String>();
    /**
     * bean properties to injection
     */
    protected List<Property> properties = new ArrayList<Property>();
    /**
     * this bean is lazy initialized
     */
    protected Boolean isLazy = false;

    public BeanDefinition() {
    }

    public void appendConstructArgs(ConstructArguments arg) {
        this.constructArgs.add(arg);
    }

    public List<ConstructArguments> getConstructArgs() {
        return constructArgs;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String newId) {
        this.id = newId;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String newClassName) {
        this.className = newClassName;
    }

    public void setIsLazy(Boolean lazy) {
        this.isLazy = lazy;
    }

    public Boolean getIsLazy() {
        return this.isLazy;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
