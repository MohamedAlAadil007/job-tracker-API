package com.aadil.jobtracker.UserService;
import com.aadil.jobtracker.UserRepository.SortDirection;
import com.aadil.jobtracker.UserRepository.UserSortField;
import com.aadil.jobtracker.Validation.UserRequestDTO;
import com.aadil.jobtracker.Validation.UserResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);

    List<UserResponseDTO> createUsers(List<UserRequestDTO> dtos);

    Optional<UserResponseDTO> getUser(Long id);

        Page<UserResponseDTO> getAllUsers(String search, int page, int size, UserSortField sortField, SortDirection sortDirection);

    List<UserResponseDTO> getUsersById(List<Long> ids);


    Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO dto);

    void deleteUser(Long id);

    void deleteAll ();

   void deleteById (List<Long>ids);

}