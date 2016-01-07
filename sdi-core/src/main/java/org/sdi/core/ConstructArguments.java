package org.sdi.core;

/**
 * Created by guoyanli on 12/25/15.
 */
public class ConstructArguments {
    private String argType;
    private String argValue;
    private String argName;
    private String argRef;

    public String getArgName() {
        return argName;
    }

    public String getArgRef() {
        return argRef;
    }

    public String getArgType() {
        return argType;
    }

    public String getArgValue() {
        return argValue;
    }

    public void setArgName(String argName) {
        this.argName = argName;
    }

    public void setArgRef(String argRef) {
        this.argRef = argRef;
    }

    public void setArgType(String argType) {
        this.argType = argType;
    }

    public void setArgValue(String argValue) {
        this.argValue = argValue;
    }
}
