package com.aadil.jobtracker.UserRepository;

import com.aadil.jobtracker.entity.UserEntity;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
    boolean existsByName(String name);

    Page<@NotNull UserEntity> findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(String name, String email, Pageable pageable);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByName(String name);

}

