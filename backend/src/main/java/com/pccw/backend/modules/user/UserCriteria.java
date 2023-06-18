package com.pccw.backend.modules.user;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.pccw.backend.persistence.PageRequest;
import lombok.Data;

@Data
public class UserCriteria {

    private String username;
    private String email;
    private Boolean active;
    private String firstName;
    private String lastName;
    private String middleName;
    @JsonUnwrapped
    private PageRequest pageRequest;

}
