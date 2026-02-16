package com.arthurneves.TaskManager.modules.users.controllers;

import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.useCases.CreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody UserEntity body) {
        try {
            UserEntity user = createUserUseCase.createUser(body);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
