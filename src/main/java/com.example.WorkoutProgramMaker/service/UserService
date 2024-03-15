package com.example.WorkoutProgramMaker.service;

import com.example.WorkoutProgramMaker.model.dto.SessionUserDTO;
import com.example.WorkoutProgramMaker.model.dto.UserLoginDTO;
import com.example.WorkoutProgramMaker.model.dto.UserRegisterDTO;
import com.example.WorkoutProgramMaker.model.entity.UserEntity;

public interface UserService {
    void save(UserRegisterDTO userRegisterDTO);

    UserLoginDTO getUserByUsernameAndPassword(String username, String password);

    UserLoginDTO getUserByEmailAndPassword(String email, String password);

    UserEntity getUserByUsername(String username);

    boolean differentPasswords(UserRegisterDTO userRegisterDTO);

    UserEntity getUserByEmail(String email);

    void giveSession(UserRegisterDTO userRegisterDTO);

    void giveSessionUsername(String emailOrUsername);

    void giveSessionEmail(String emailOrUsername);

    void logout(SessionUserDTO user);

    boolean usedUsername(UserRegisterDTO userRegisterDTO);

    boolean usedEmail(UserRegisterDTO userRegisterDTO);

    UserEntity getByID(Long id);
}
