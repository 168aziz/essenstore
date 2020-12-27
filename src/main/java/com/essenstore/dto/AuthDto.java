package com.essenstore.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AuthDto {

    @Email(message = "email is not valid")
    @NotBlank(message = "email is empty")
    private String email;

    @NotBlank(message = "password is empty")
    private String password;

}
