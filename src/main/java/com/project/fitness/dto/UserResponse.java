package com.project.fitness.dto;

import com.project.fitness.model.User;
import com.project.fitness.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    public UserResponse(User savedUser) {
        this.id = savedUser.getId();
        this.email = savedUser.getEmail();
        this.firstName = savedUser.getFirstName();
        this.lastName = savedUser.getLastName();
        this.createdAt = savedUser.getCreatedAt();
        this.updatedAt = savedUser.getUpdatedAt();
        this.role = savedUser.getRole();
    }
    private String id ;
    private String email ;
    private UserRole role;
    private String firstName ;
    private String lastName ;
    private LocalDateTime createdAt ;
    private LocalDateTime updatedAt ;
}
