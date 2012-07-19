package org.motechproject.ananya.kilkari.reports.web.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Ignore;

import java.io.IOException;
import java.io.StringWriter;

@Ignore
public class TestUtils {
    public static String toJson(Object objectToSerialize) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();
        try {
            mapper.writeValue(stringWriter, objectToSerialize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }

}
