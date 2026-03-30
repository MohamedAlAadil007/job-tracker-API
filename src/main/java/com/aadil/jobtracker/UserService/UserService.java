package com.aadil.jobtracker.UserService;

import com.aadil.jobtracker.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity createUser(UserEntity user);

    List<UserEntity> createUsers(List<UserEntity>users);

    UserEntity getUser(Long id);


    List<UserEntity> getAllUsers (List<UserEntity>users);

    List<UserEntity> getAllUsersById(List<Long>ids);

    UserEntity updateUser (Long id,UserEntity user);

    String deleteUser (Long id);

    String deleteAll (String confirm);

    String deleteById (List<Long>ids);

}
