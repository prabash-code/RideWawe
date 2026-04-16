package edu.icet.service;

import edu.icet.model.dto.response.UserResponse;
import edu.icet.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity findUserById(Long id);

    void deleteUserById(Long id);

    UserEntity findUserByEmail(String email);
}
