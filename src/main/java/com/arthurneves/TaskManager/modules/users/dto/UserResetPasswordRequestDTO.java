package com.arthurneves.TaskManager.modules.users.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserResetPasswordRequestDTO {
    @NotBlank
    @Length(min = 10, max = 72)
    private String password;

    @NotBlank
    @Length(min = 10, max = 72)
    private String newPassword;
}
