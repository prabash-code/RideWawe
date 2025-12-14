package edu.icet.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    private Long userId;
    private String username;
    private String email;
    private String NIC;
    private String phone;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
