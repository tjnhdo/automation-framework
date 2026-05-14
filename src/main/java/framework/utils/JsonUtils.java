package framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonUtils() {
    }

    public static <T> T readJson(Path path, Class<T> clazz) {
        try {
            return MAPPER.readValue(Files.newBufferedReader(path), clazz);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read JSON file: " + path, e);
        }
    }
}

