package com.example.WorkoutProgramMaker.model.entity;

import com.example.WorkoutProgramMaker.model.entity.enums.ExerciseType;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Table(name = "exercises")
public class ExerciseEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExerciseType exerciseType;
    @Column(name = "kg")
    private Double kg;
    @Column(name = "reps")
    private Integer reps;
    @Column(name = "duration")
    private Integer duration;
    /* in seconds */
    @Column(name = "number")
    private Integer number;

    @ManyToOne
    private WorkoutEntity workout;
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;


    @Column(name = "sets")
    private Integer numberOfSets;

    public ExerciseEntity() {
    }

    public WorkoutEntity getWorkout() {
        return workout;
    }

    public void setWorkout(WorkoutEntity workout) {
        this.workout = workout;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(ExerciseType exerciseType) {
        this.exerciseType = exerciseType;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getNumberOfSets() {
        return numberOfSets;
    }

    public void setNumberOfSets(Integer numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
