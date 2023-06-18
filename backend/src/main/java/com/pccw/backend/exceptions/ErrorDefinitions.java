package com.pccw.backend.exceptions;


import lombok.Getter;

@Getter
public enum ErrorDefinitions implements ErrorDefinition {

    DUPLICATE_USERNAME("Username already exists"),
    DUPLICATE_EMAIL("Email already exists");

    private final String message;

    ErrorDefinitions(String message) {
        this.message = message;
    }

}
