package com.testapp.demo.users.controllers;

import com.testapp.demo.users.models.CreateUserRequest;
import com.testapp.demo.users.models.UpdateUserRequest;
import com.testapp.demo.users.models.User;
import com.testapp.demo.users.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody CreateUserRequest r){
        Optional<User> user1 = userRepository.findUserByEmail(r.getEmail());
        if (user1.isPresent()){
            return  ResponseEntity.status(400).body("User already exists.");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(r.getEmail());
        user.setPassword(r.getPassword());
        userRepository.save(user);
        return ResponseEntity.status(200).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserRequest r){
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()){
             return  ResponseEntity.status(404).body("User not found.");
        }
        User user = user1.get();
        user.setEmail(r.getEmail());
        user.setPassword(r.getPassword());
        userRepository.save(user);
        return ResponseEntity.status(200).body(user);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty()){
            return  ResponseEntity.status(404).body("User not found.");
        }
        userRepository.delete(user1.get());
        return ResponseEntity.status(200).body(user1.get());

    }
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}

