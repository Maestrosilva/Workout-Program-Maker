package com.example.WorkoutProgramMaker.service;

import com.example.WorkoutProgramMaker.model.dto.SessionUserDTO;
import com.example.WorkoutProgramMaker.model.dto.UserLoginDTO;
import com.example.WorkoutProgramMaker.model.dto.UserRegisterDTO;
import com.example.WorkoutProgramMaker.model.entity.UserEntity;
import com.example.WorkoutProgramMaker.model.entity.enums.Level;
import com.example.WorkoutProgramMaker.model.entity.enums.Role;
import com.example.WorkoutProgramMaker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final SessionUserDTO user;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, SessionUserDTO user) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.user = user;
    }

    @Override
    public void save(UserRegisterDTO userRegisterDTO) {
        UserEntity user = modelMapper.map(userRegisterDTO, UserEntity.class);
        user.setLevel(Level.BEGINNER);
        user.setRole(Role.CUSTOMER);
        user.setSingUpDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public UserLoginDTO getUserByUsernameAndPassword(String username, String password) {
        UserEntity userEntity = userRepository
                .getByUsernameAndPassword(username, password)
                .orElse(null);
        if (userEntity == null) {
            return null;
        }
        return modelMapper.map(userEntity, UserLoginDTO.class);
    }

    @Override
    public UserLoginDTO getUserByEmailAndPassword(String email, String password) {
        UserEntity userEntity = userRepository
                .getByEmailAndPassword(email, password)
                .orElse(null);
        if (userEntity == null) {
            return null;
        }
        return modelMapper.map(userEntity, UserLoginDTO.class);
    }


    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.getByUsername(username).orElse(null);
    }

    @Override
    public boolean differentPasswords(UserRegisterDTO userRegisterDTO) {
        String password = userRegisterDTO.getPassword();
        String confirmPassword = userRegisterDTO.getConfirmPassword();
        return !password.equals(confirmPassword);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository
                .getByEmail(email)
                .orElse(null);
    }

    @Override
    public void giveSession(UserRegisterDTO userRegisterDTO) {
        String email = userRegisterDTO.getEmail();
        String username = userRegisterDTO.getUsername();
        Long userId = getUserByEmail(email).getId();
        user.setID(userId);
        user.setUsername(username);
        user.setEmail(email);
    }

    @Override
    public void giveSessionUsername(String emailOrUsername) {
        Long userId = getUserByUsername(emailOrUsername).getId();
        user.setID(userId);
        user.setUsername(emailOrUsername);
    }

    @Override
    public void giveSessionEmail(String emailOrUsername) {
        Long userId = getUserByEmail(emailOrUsername).getId();
        user.setID(userId);
        user.setEmail(emailOrUsername);
        user.setUsername(userRepository.getById(userId).getUsername());
    }

    @Override
    public void logout(SessionUserDTO user) {
        user.setID(null);
    }

    @Override
    public boolean usedUsername(UserRegisterDTO userRegisterDTO) {
        String username = userRegisterDTO.getUsername();
        return getUserByUsername(username) != null;
    }

    @Override
    public boolean usedEmail(UserRegisterDTO userRegisterDTO) {
        String email = userRegisterDTO.getEmail();
        return getUserByEmail(email) != null;
    }

    @Override
    public UserEntity getByID(Long id) {
        return userRepository.getById(id);
    }
}
