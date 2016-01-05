package org.sdi.factory;

import org.sdi.common.IO;

import java.io.Reader;

/**
 * Created by guoyanli on 12/29/15.
 */
public class JsonBeanFactory extends AbstractJsonBeanFactory {

    public JsonBeanFactory(Reader reader) throws Exception {
        super(IO.extractJson(reader));
    }
}
