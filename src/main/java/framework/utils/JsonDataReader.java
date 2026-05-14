package framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public final class JsonDataReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonDataReader() {
    }

    public static <T> Object[][] readData(String fileName, Class<T> clazz) {
        try {
            // Sử dụng đường dẫn tuyệt đối dựa trên thư mục gốc của dự án
            File file = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "testdata", fileName).toFile();
            
            List<T> list = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
            
            Object[][] data = new Object[list.size()][1];
            for (int i = 0; i < list.size(); i++) {
                data[i][0] = list.get(i);
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON test data file: " + fileName, e);
        }
    }
}