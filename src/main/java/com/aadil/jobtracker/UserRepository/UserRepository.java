package com.aadil.jobtracker.UserRepository;

import com.aadil.jobtracker.entity.UserEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
    boolean existsByName(String name);

    Page<UserEntity> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name,String email,Pageable pageable);
}

