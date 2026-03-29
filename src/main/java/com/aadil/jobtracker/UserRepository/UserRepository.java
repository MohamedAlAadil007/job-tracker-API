package com.aadil.jobtracker.UserRepository;

import com.aadil.jobtracker.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, UserEntity> {
}
