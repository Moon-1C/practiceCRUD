package com.example.demo.repository.repository;

import com.example.demo.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByName(String name);

    Optional<UserEntity> findByNameAndDeletedFalse(String name);

    Optional<UserEntity> findByIdAndDeletedFalse(Long Id);
}
