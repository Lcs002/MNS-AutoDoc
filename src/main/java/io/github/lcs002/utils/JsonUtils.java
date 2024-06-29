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

    public static Map<String, Object> fileToMap(String filePath) {
        File file = new File(filePath);
        try {
            return objectMapper.readValue(file, typeRef);
        }
        catch (IOException e) {
            System.out.println("Error while reading file '" + filePath + "'.\n" + e.getMessage());
            return new HashMap<>();
        }
    }

    public static Map<String, Object> stringToMap(String content) {
        try {
            return objectMapper.readValue(content, typeRef);
        }
        catch (IOException e) {
            System.out.println("Error while reading content: \n " + content + "\n" + e.getMessage());
            return new HashMap<>();
        }
    }
}
