package com.aadil.jobtracker.UserRepository;

import com.aadil.jobtracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
    boolean existsByName(String name);


    List<UserEntity> findByEmailIn(List<String> emails);

    List<UserEntity> findByNameIn(List<String> names);
}

