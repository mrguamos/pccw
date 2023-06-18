package com.pccw.backend.modules.user;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.Valid;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateUserInput implements Serializable {

    @JsonUnwrapped
    @Valid
    private Name name;

}
