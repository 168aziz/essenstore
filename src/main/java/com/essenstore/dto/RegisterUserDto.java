package com.essenstore.dto;

import com.essenstore.service.UserService;
import com.essenstore.validator.PasswordMatch;
import com.essenstore.validator.Unique;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@PasswordMatch(field = "password", fieldMatch = "confirmPassword")
public class RegisterUserDto {

    @NotBlank(message = "Surname is empty")
    @Size(max = 250, message = "Surname is too long")
    private String surname;

    @NotBlank(message = "Name is empty")
    @Size(max = 250, message = "Name is too long")
    private String name;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is empty")
    @Unique(service = UserService.class)
    private String email;

    @NotBlank(message = "Password is empty")
    @Size(min = 8, message = "Password min length 8")
    private String password;

    @JsonProperty("confirm-password")
    @NotBlank(message = "Confirm password is empty")
    @Size(min = 8, message = "Confirm password min length 8")
    private String confirmPassword;

}
