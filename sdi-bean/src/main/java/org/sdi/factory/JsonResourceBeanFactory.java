package org.sdi.factory;

import org.sdi.common.IO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author guoyanli
 */
public class JsonResourceBeanFactory extends AbstractJsonBeanFactory {
    private static final Map<String, JsonResourceBeanFactory> resourceToFactorySingleInstance =
            new ConcurrentHashMap<String, JsonResourceBeanFactory>();

    private JsonResourceBeanFactory(String resourceFileName) throws Exception {
        super(IO.extractJsonFromResource(resourceFileName));
    }

    /**
     *
     * @param resourceFileName
     * @return
     * @throws Exception
     */
    public static JsonResourceBeanFactory getFactory(String resourceFileName) throws Exception {
        if (resourceFileName == null) {
            return null;
        }
        if (!resourceToFactorySingleInstance.containsKey(resourceFileName)) {
            resourceToFactorySingleInstance.put(resourceFileName, new JsonResourceBeanFactory(resourceFileName));
        }

        return resourceToFactorySingleInstance.get(resourceFileName);
    }
}
