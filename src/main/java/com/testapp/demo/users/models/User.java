package com.testapp.demo.users.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

@Entity
@Data
public class User {

@Id
    private String id;
private String email;
private String password;

}
