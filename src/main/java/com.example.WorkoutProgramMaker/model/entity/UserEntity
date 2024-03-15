package com.example.WorkoutProgramMaker.model.entity;
import com.example.WorkoutProgramMaker.model.entity.enums.Level;
import com.example.WorkoutProgramMaker.model.entity.enums.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "age", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Level level;
    @OneToMany()
    private Set<ExerciseEntity> oldExercises;

    @Column(name = "sign_up_date")
    private LocalDateTime singUpDate;

    public Set<ExerciseEntity> getOldExercises() {
        return oldExercises;
    }

    public void setOldExercises(Set<ExerciseEntity> oldExercises) {
        this.oldExercises = oldExercises;
    }

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public LocalDateTime getSingUpDate() {
        return singUpDate;
    }

    public void setSingUpDate(LocalDateTime singUpDate) {
        this.singUpDate = singUpDate;
    }
}
