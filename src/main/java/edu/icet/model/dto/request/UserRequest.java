package edu.icet.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotNull
    private String userName;

    //@NotBlank(message = "Email is required")
//    @Email(message = "Invalid email format")
//    private String email;

    @NotBlank(message = "Password is required")
    private String password;


}


