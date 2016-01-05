package org.sdi.core;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyanli on 12/25/15.
 */
public class ConstructedBean extends BeanDefinition {

    public static final String INITIAL_METHOD_NAME = "initMethod";

    private String initMethod;

    public ConstructedBean() {
        super();

        properties = new ArrayList<Property>();
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }
}
