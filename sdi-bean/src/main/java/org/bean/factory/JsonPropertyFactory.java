package org.sdi.factory;

import com.google.gson.JsonObject;

import org.sdi.core.Property;

/**
 * Created by guoyanli on 12/31/15.
 */
public class JsonPropertyFactory {
    public static Property getProperty(JsonObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        // todo

        Property property = new Property();
        property.setPropertyName(jsonObject.get("name").getAsString());
        if (jsonObject.has("value")) {
            property.setPropertyValue(jsonObject.get("value").getAsString());
        }
        if (jsonObject.has("ref")) {
            property.setPropertyRef(jsonObject.get("ref").getAsString());
        }

        if (property.getPropertyValue() == null && property.getPropertyRef() == null) {
            return null;
        }

        return property;
    }
}
