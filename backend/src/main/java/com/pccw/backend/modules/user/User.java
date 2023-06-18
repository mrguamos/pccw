package com.pccw.backend.modules.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pccw.backend.utils.Patterns;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class User implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Valid
    @JsonUnwrapped
    private Name name;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private boolean status;
    private LocalDateTime registrationTime;

    @Builder
    public User(Long id, Name name, String username, String password, String email, boolean status, LocalDateTime registrationTime) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.registrationTime = registrationTime;
    }

    @AssertTrue(message = "Invalid Username")
    @JsonIgnore
    public boolean isValidUsername() {
        return StringUtils.isNotEmpty(this.username) && !Patterns.usernamePattern.matcher(this.username).find();
    }

    @AssertTrue(message = "Invalid email address")
    @JsonIgnore
    public boolean isValidEmail() {
        return StringUtils.isNotEmpty(this.email) && Patterns.emailPattern.matcher(this.email).matches();
    }

}