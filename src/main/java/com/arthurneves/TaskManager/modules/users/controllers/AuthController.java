package com.arthurneves.TaskManager.modules.users.controllers;

import com.arthurneves.TaskManager.modules.users.dto.UserLoginRequestDTO;
import com.arthurneves.TaskManager.modules.users.entity.UserEntity;
import com.arthurneves.TaskManager.modules.users.useCases.RegisterUseCase;
import com.arthurneves.TaskManager.modules.users.useCases.LoginUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private RegisterUseCase registerUseCase;

    @Autowired
    private LoginUseCase loginUseCase;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserEntity body) {
        try {
            UserEntity user = registerUseCase.execute(body);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody UserLoginRequestDTO body) {
        try {
            Object result = loginUseCase.execute(body);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
