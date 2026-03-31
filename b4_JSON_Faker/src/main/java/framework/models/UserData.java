package framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private String role;

    @JsonProperty("expectSuccess")
    private boolean expectSuccess;

    @JsonProperty("description")
    private String description;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    // 🔥 QUAN TRỌNG: getter đúng chuẩn boolean
    public boolean isExpectSuccess() { return expectSuccess; }

    public String getDescription() { return description; }
}