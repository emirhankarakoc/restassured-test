package com.testapp.demo.users.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UpdateUserRequest {

    private String email;

    private String password;
}
