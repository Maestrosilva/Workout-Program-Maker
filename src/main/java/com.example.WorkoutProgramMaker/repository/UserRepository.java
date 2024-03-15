package com.example.WorkoutProgramMaker.repository;

import com.example.WorkoutProgramMaker.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username AND u.password = :password")
    Optional<UserEntity> getByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password);

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email AND u.password = :password")
    Optional<UserEntity> getByEmailAndPassword(
            @Param("email") String email,
            @Param("password") String password);

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> getByUsername(
            @Param("username") String username);

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    Optional<UserEntity> getByEmail(
            @Param("email") String email);

}
