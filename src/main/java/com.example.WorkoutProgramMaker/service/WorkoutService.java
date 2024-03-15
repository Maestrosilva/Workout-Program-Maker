package com.example.WorkoutProgramMaker.service;

import com.example.WorkoutProgramMaker.model.dto.WorkoutDTO;
import com.example.WorkoutProgramMaker.model.entity.ExerciseEntity;
import com.example.WorkoutProgramMaker.model.entity.UserEntity;
import com.example.WorkoutProgramMaker.model.entity.WorkoutEntity;

import java.util.Set;

public interface WorkoutService {
    void save(WorkoutDTO workoutDTO);

    void save(WorkoutEntity workoutDTO);

    Set<WorkoutDTO> getAllWorkouts(UserEntity id);

    WorkoutDTO findByUserAndName(String username, String workoutName);

    void delete(String user, WorkoutDTO workoutDTO);

    WorkoutDTO findByExercise(ExerciseEntity currentExercise);

    void removeExerciseFromWorkoutWithName(String username, ExerciseEntity exercise, String workout);
}
