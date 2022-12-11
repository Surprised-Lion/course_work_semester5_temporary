package org.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LoginDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;

    @JsonCreator
    public LoginDto(
            @JsonProperty(value = "username", required = true) String username,
            @JsonProperty(value = "password", required = true) String password) {
        this.username = username;
        this.password = password;
    }
}
