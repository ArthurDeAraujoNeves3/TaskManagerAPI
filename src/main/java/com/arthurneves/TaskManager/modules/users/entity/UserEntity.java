package com.arthurneves.TaskManager.modules.users.entity;

import com.arthurneves.TaskManager.modules.tasks.entities.TaskEntity;
import com.arthurneves.TaskManager.modules.teams.entities.TeamEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity(name = "Users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "\\S+", message = "username não pode conter espaços")
    private String username;
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 10, max = 72)
    private String password;

    @CreationTimestamp
    private LocalDateTime create_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "members")
    @JsonBackReference
    private List<TaskEntity> tasks;
}
