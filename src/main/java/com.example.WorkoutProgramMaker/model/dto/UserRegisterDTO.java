package com.example.WorkoutProgramMaker.model.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.*;

public class UserRegisterDTO {
    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Invalid email.")
    private String email;
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 5, message = "Username must be at least 5 symbols")
    private String username;
    @NotNull
    @Min(value = 10, message = "Age should be between 10 and 120")
    @Max(value = 120, message = "Age should be between 10 and 120")
    private Integer age;
    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 5, message = "Username must be at least 5 symbols")
    private String password;
    @NotBlank
    @Size(min = 5)
    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
