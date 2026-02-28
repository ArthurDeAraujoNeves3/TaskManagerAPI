package com.arthurneves.TaskManager.modules.users.controllers;

import com.arthurneves.TaskManager.modules.users.dto.UserGetInfoRequestDTO;
import com.arthurneves.TaskManager.modules.users.dto.UserResetPasswordRequestDTO;
import com.arthurneves.TaskManager.modules.users.dto.UserUpdateRequestDTO;
import com.arthurneves.TaskManager.modules.users.useCases.GetUserInfoUseCase;
import com.arthurneves.TaskManager.modules.users.useCases.ResetPasswordUseCase;
import com.arthurneves.TaskManager.modules.users.useCases.UpdateUserDataUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private GetUserInfoUseCase getUserInfoUseCase;
    @Autowired
    private UpdateUserDataUseCase updateUserDataUseCase;
    @Autowired
    private ResetPasswordUseCase resetPasswordUseCase;

    @GetMapping("/info")
    public ResponseEntity<Object> returnUserInfo(@RequestHeader("Authorization") String token) {
        try {
            UserGetInfoRequestDTO result = getUserInfoUseCase.execute(token);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateUserInfo(@RequestHeader("Authorization") String token, @RequestBody UserUpdateRequestDTO data) {
        try {
            UserUpdateRequestDTO result = updateUserDataUseCase.execute(token, data);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/resetpassword")
    public ResponseEntity<Object> resetUserPassword(@RequestHeader("Authorization") String token, @Valid @RequestBody UserResetPasswordRequestDTO data) {
        try {
            this.resetPasswordUseCase.execute(token, data);
            return ResponseEntity.ok().body("Senha atualizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
