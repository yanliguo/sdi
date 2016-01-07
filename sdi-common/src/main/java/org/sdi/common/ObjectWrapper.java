package org.sdi.common;

/**
 * Created by guoyanli on 12/29/15.
 */
public class ObjectWrapper {
    public Object object;
    public Boolean triedToInitial = Boolean.FALSE;

    public ObjectWrapper(Object object1) {
        this.object = object1;
        this.triedToInitial = Boolean.TRUE;
    }
}
