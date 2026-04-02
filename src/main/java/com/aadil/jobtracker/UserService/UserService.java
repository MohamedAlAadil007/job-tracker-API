package com.aadil.jobtracker.UserService;

import com.aadil.jobtracker.Validation.UserRequestDTO;
import com.aadil.jobtracker.Validation.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);

    List<UserResponseDTO> createUsers(List<UserRequestDTO> dtos);

    Optional<UserResponseDTO> getUser(Long id);

    List<UserResponseDTO> getUsersById(List<Long> ids);

    List<UserResponseDTO> getAllUsers();

    Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO dto);

    void deleteUser(Long id);

    void deleteAll ();

   void deleteById (List<Long>ids);

}