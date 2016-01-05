package org.sdi.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * Created by guoyanli on 12/29/15.
 */
public class IO {

    /**
     * return a resource file reader
     * @param resourceFileName relative path of resource file
     * @return file reader
     * @throws Exception when this file is not readable
     * @see Reader
     */
    public static Reader resourceFileReader(String resourceFileName) throws Exception {
        ClassLoader classLoader = IO.class.getClassLoader();
        File file = new File(classLoader.getResource(resourceFileName).getFile());
        if (!file.canRead()) {
            throw new IOException("file can not be read" + resourceFileName);
        }

        return new FileReader(file);
    }

    /**
     * return a parsed {@link JsonObject} with given {@link Reader}
     * @param reader
     * @return
     * @throws IOException
     * @see JsonObject
     * @see JsonParser
     * @see Gson
     */
    public static JsonObject extractJson(Reader reader) throws IOException {
        // todo reader stronger
        char[] buf = new char[1000000];
        int size = reader.read(buf);
        char[] buf2 = new char[size];
        System.arraycopy(buf, 0, buf2, 0, size);
        return new JsonParser().parse(String.valueOf(buf2)).getAsJsonObject();
    }

    /**
     *
     * @param resourceFileName relative path of resource file
     * @return parsed {@link JsonObject} with a given resource file name
     * @throws Exception
     * @see JsonObject
     */
    public static JsonObject extractJsonFromResource(String resourceFileName) throws Exception {
        return IO.extractJson(IO.resourceFileReader(resourceFileName));
    }
}
