package org.sdi.core;

/**
 * Created by guoyanli on 12/25/15.
 */
public class FactoryBean extends BeanDefinition {

    public static final String FACTORY_METHOD_NAME = "factory-method";

    private String factoryMethod;

    public FactoryBean() {
        super();
    }

    public String getFactoryMethod() {
        return factoryMethod;
    }

    public void setFactoryMethod(String factoryMethod) {
        this.factoryMethod = factoryMethod;
    }
}
