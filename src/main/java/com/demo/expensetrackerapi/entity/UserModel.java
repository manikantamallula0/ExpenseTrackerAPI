package com.demo.expensetrackerapi.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserModel {

    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotNull(message = "email should not be empty")
    @Email(message = "enter Valid email")
    private String email;

    @NotNull(message = "Password should not be emply")
    @Size(min = 5, max = 16, message = "Password should be atlease 5 charecters and not more than 16 charecters")
    private String password;

    private Long age;

}
