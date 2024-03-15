package com.example.WorkoutProgramMaker.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "workouts")
public class WorkoutEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "workout")
    private Set<ExerciseEntity> exercises;
    @ManyToOne
    private UserEntity user;


    public WorkoutEntity() {
    }

    public Set<ExerciseEntity> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseEntity> exercises) {
        this.exercises = exercises;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
