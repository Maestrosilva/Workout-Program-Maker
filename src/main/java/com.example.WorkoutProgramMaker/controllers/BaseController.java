package com.example.WorkoutProgramMaker.controllers;

import com.example.WorkoutProgramMaker.model.dto.SessionUser;
import com.example.WorkoutProgramMaker.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BaseController {
    private final UserService userService;
    private final HttpSession session;
    private final ModelMapper modelMapper;
    private final SessionUser user;

    public BaseController(UserService userService, HttpSession session, ModelMapper modelMapper, SessionUser user) {
        this.userService = userService;
        this.session = session;
        this.modelMapper = modelMapper;
        this.user = user;
    }

    @GetMapping("/")
    public String loadPage(){
        boolean isLogged = user.getID() != null;
        if(isLogged){
            return "redirect:/home";
        }
        return "redirect:/login";
    }
}
