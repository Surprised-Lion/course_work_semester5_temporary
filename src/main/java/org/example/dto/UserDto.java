package org.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.common.GenderType;

import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
public class UserDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfBirth;
    @JsonProperty("email")
    private String email;
    @JsonProperty("genderType")
    private GenderType genderType;
    @JsonProperty("roleId")
    private Long roleId;

    @JsonCreator
    public UserDto(
            @JsonProperty(value = "id") Long id,
            @JsonProperty(value = "username", required = true) String username,
            @JsonProperty(value = "password", required = true) String password,
            @JsonProperty(value = "email", required = true) String email,
            @JsonProperty(value = "dateOfBirth", required = true)
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") Date dateOfBirth,
            @JsonProperty(value = "genderType", required = true) GenderType genderType,
            @JsonProperty(value = "roleId", required = true) Long roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.genderType = genderType;
        this.roleId = roleId;
    }
}
