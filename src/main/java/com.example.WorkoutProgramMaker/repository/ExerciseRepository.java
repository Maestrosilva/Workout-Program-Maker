package com.example.WorkoutProgramMaker.repository;

import com.example.WorkoutProgramMaker.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    @Override
    @Query("SELECT e FROM ExerciseEntity e WHERE e.id = :id")
    ExerciseEntity getById(@Param("id") Long id);
}
