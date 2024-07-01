package io.github.lcs002.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {};

    public static Map<String, Object> stringToMap(String content) {
        try {
            return objectMapper.readValue(content, typeRef);
        }
        catch (IOException e) {
            Printer.printDebug("Error while reading content: \n " + content + "\n" + e.getMessage());
            return new HashMap<>();
        }
    }
}
