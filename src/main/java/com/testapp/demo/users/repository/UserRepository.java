package com.testapp.demo.users.repository;

import com.testapp.demo.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {


    Optional<User> findUserByEmail(String email);
}
