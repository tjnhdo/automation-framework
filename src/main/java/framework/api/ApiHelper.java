package framework.api;

import framework.config.ConfigManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiHelper {
    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    /**
     * Ví dụ: Gọi API để tạo một User mới trước khi test UI.
     * Giúp test UI không phải đi qua luồng Register rườm rà.
     */
    public static String createTestUserViaApi(String email, String password) {
        String jsonBody = String.format("{\"email\":\"%s\", \"password\":\"%s\"}", email, password);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ConfigManager.getBaseApiUrl() + "/users"))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(15))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 201) {
                throw new RuntimeException("API Setup Failed: " + response.body());
            }
            return response.body(); // Trả về data (VD: User ID) nếu cần dọn dẹp sau này
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi gọi API tạo dữ liệu: " + e.getMessage(), e);
        }
    }
}