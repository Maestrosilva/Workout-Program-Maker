package com.example.WorkoutProgramMaker.service;

import com.example.WorkoutProgramMaker.model.entity.ExerciseEntity;
import com.example.WorkoutProgramMaker.model.entity.WorkoutEntity;

import java.util.Set;

public interface ExerciseService {
    Set<ExerciseEntity> getExerciseList(String data);

    void setWorkout(ExerciseEntity exercise, WorkoutEntity workout);

    void save(ExerciseEntity exercise);

    ExerciseEntity getExerciseById(Long exercise);

    void remove(ExerciseEntity currentExercise);
}
