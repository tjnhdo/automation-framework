package framework.models;

/**
 * POJO Model mapping trực tiếp với các key trong file loginData.json
 */
public class LoginData {
    private String email;
    private String password;
    private String expectedErrorMessage;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpectedErrorMessage() {
        return expectedErrorMessage;
    }

    public void setExpectedErrorMessage(String expectedErrorMessage) {
        this.expectedErrorMessage = expectedErrorMessage;
    }
}
