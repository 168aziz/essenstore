package com.essenstore.dto;

import com.essenstore.service.UserService;
import com.essenstore.validator.PasswordMatch;
import com.essenstore.validator.Unique;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@PasswordMatch(field = "password", fieldMatch = "confirmPassword")
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)
public class RegisterUserDto {

    @NotBlank
    @Size(max = 250, min = 1)
    private String surname;

    @NotBlank
    @Size(max = 250, min = 1)
    private String name;

    @Email
    @NotBlank
    @Unique(service = UserService.class)
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Size(min = 8)
    private String confirmPassword;


    public void setSurname(String surname) {
        this.surname = StringUtils.normalizeSpace(surname.trim());
    }

    public void setName(String name) {
        this.name = StringUtils.normalizeSpace(name.trim());
    }

    public void setEmail(String email) {
        this.email = StringUtils.normalizeSpace(email.trim());
    }
}
