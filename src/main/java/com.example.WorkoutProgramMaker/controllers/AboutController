package com.example.WorkoutProgramMaker.controllers;

import com.example.WorkoutProgramMaker.model.dto.SessionUser;
import com.example.WorkoutProgramMaker.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
public class AboutController {
    private final UserService userService;
    private final HttpSession session;
    private final ModelMapper modelMapper;
    private final SessionUser user;

    public AboutController(UserService userService, HttpSession session, ModelMapper modelMapper, SessionUser user) {
        this.userService = userService;
        this.session = session;
        this.modelMapper = modelMapper;
        this.user = user;
    }

    @GetMapping("/about")
    public String about(Model model){
        boolean isLogged = user.getID() != null;
        model.addAttribute("isLogged", isLogged);
        return "about";
    }
}
