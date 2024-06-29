package io.github.lcs002.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResourceUtils {
    public static String getResourceAsString(String filepath) throws IOException {
        try (var in = ResourceUtils.class.getResourceAsStream("/" + filepath)) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
