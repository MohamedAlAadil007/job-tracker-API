package com.aadil.jobtracker.UserService;

import com.aadil.jobtracker.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity createUser(UserEntity user);

    List<UserEntity> createUsers(List<UserEntity>users);

    Optional<UserEntity> getUser(Long id);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> updateUser (Long id, UserEntity user);

    void deleteUser (Long id);

    void deleteAll();

    void deleteById (List<Long>ids);

     void validateUser(UserEntity user);


}
