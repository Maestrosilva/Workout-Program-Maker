package com.example.WorkoutProgramMaker.model.dto;
import com.example.WorkoutProgramMaker.model.entity.ExerciseEntity;
import com.example.WorkoutProgramMaker.model.entity.UserEntity;

import java.util.Set;

public class WorkoutDTO {
    private Set<ExerciseEntity> exercises;
    private String name;
    private UserEntity user;

    public WorkoutDTO() {
    }

    public Set<ExerciseEntity> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseEntity> exercises) {
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
