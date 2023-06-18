package com.pccw.backend.modules.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pccw.backend.utils.Patterns;
import jakarta.validation.constraints.AssertTrue;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
public class Name {

    private String firstName;
    private String middleName;
    private String lastName;

    @Builder
    public Name(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    @AssertTrue(message = "Invalid first name")
    @JsonIgnore
    public boolean isValidFirstName() {
        return StringUtils.isNotEmpty(this.firstName) && Patterns.namePattern.matcher(this.firstName).matches();
    }

    @AssertTrue(message = "Invalid middle name")
    @JsonIgnore
    public boolean isValidMiddleName() {
        if (StringUtils.isNotEmpty(this.middleName)) {
            return Patterns.namePattern.matcher(this.middleName).matches();
        }
        return true;
    }

    @AssertTrue(message = "Invalid last name")
    @JsonIgnore
    public boolean isValidLastName() {
        return StringUtils.isNotEmpty(this.lastName) && Patterns.namePattern.matcher(this.lastName).matches();
    }

    public String getEffectiveName() {
        return String.format("%s %s %s",
                this.firstName,
                StringUtils.isEmpty(this.middleName) ? "" : this.middleName,
                this.lastName);
    }
}
