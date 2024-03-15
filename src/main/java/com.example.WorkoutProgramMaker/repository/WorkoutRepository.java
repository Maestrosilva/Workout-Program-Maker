package com.example.WorkoutProgramMaker.repository;

import com.example.WorkoutProgramMaker.model.entity.UserEntity;
import com.example.WorkoutProgramMaker.model.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity, Long> {
    @Query("SELECT w FROM WorkoutEntity w WHERE w.user = :user")
    Set<WorkoutEntity> getByUser(@Param("user")
                                        UserEntity user);
    @Query("SELECT w FROM WorkoutEntity w WHERE w.user.username =:username AND w.name =:workoutName")
    Set<WorkoutEntity> getByUserAndName(
            @Param("username") String username,
            @Param("workoutName") String workoutName);
}
