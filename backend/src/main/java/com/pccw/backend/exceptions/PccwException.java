package com.pccw.backend.exceptions;


import lombok.Getter;
import lombok.NonNull;


@Getter
public class PccwException extends RuntimeException {

    private PccwException(String message) {
        super(message);
    }

    public static PccwException of(@NonNull ErrorDefinition errorDefinition, Object... args) {
        return new PccwException(String.format(errorDefinition.getMessage(), args));
    }

}
