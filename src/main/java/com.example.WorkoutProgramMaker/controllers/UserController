package com.example.WorkoutProgramMaker.controllers;
import com.example.WorkoutProgramMaker.model.dto.SessionUser;
import com.example.WorkoutProgramMaker.model.dto.UserLoginDTO;
import com.example.WorkoutProgramMaker.model.dto.UserRegisterDTO;
import com.example.WorkoutProgramMaker.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;
    private final ModelMapper modelMapper;
    private final SessionUser user;



    public UserController(UserService userService, HttpSession session, ModelMapper modelMapper, SessionUser user) {
        this.userService = userService;
        this.session = session;
        this.modelMapper = modelMapper;
        this.user = user;
    }

    @ModelAttribute("registerDTO")
    public UserRegisterDTO initRegistrationDTO() {
        return new UserRegisterDTO();
    }
    @ModelAttribute("loginDTO")
    public UserLoginDTO initLoginDTO() {
        return new UserLoginDTO();
    }
//    @ModelAttribute("currentWorkout")
//    public String currentWorkout(){
//        return "";
//    }

    @GetMapping("/register")
    public String getRegister(Model model, RedirectAttributes redirectAttributes) {
        boolean isLogged = user.getID() != null;
        model.addAttribute("isLogged", isLogged);
        redirectAttributes.addFlashAttribute("usedUsername", false);
        redirectAttributes.addFlashAttribute("usedEmail", false);
        redirectAttributes.addFlashAttribute("differentPasswords", false);
        return "register-page";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterDTO userRegisterDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        boolean usedUsername = userService.usedUsername(userRegisterDTO);
        boolean usedEmail = userService.usedEmail(userRegisterDTO);
        boolean differentPasswords = userService.differentPasswords(userRegisterDTO);
        boolean invalidConstraints = usedUsername || usedEmail || differentPasswords;
        System.out.println(userRegisterDTO);
        if (bindingResult.hasErrors() || invalidConstraints) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", userRegisterDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            redirectAttributes.addFlashAttribute("usedUsername", usedUsername);
            redirectAttributes.addFlashAttribute("usedEmail", usedEmail);
            redirectAttributes.addFlashAttribute("differentPasswords", differentPasswords);
            return "redirect:/register";
        }
        userService.save(userRegisterDTO);
        userService.giveSession(userRegisterDTO);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String getLogin(Model model, RedirectAttributes redirectAttributes) {
        boolean isLogged = user.getID() != null;
        model.addAttribute("isLogged", isLogged);
        redirectAttributes
                .addFlashAttribute("userNotFound", false);
        return "login-page";
    }

    @PostMapping("/login")
    public String postLogin(@Valid UserLoginDTO userLoginDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        System.out.println(userLoginDTO);
        String EmailOrUsername = userLoginDTO.getEmailOrUsername();
        String password = userLoginDTO.getPassword();
        UserLoginDTO passUser = userService.getUserByUsernameAndPassword(EmailOrUsername, password);
        UserLoginDTO emailUser = userService.getUserByEmailAndPassword(EmailOrUsername, password);
        if (emailUser != null) {
            userService.giveSessionEmail(EmailOrUsername);
            return "redirect:/home";
        }
        if (passUser != null) {
            userService.giveSessionUsername(EmailOrUsername);
            return "redirect:/home";
        }
        redirectAttributes
                .addFlashAttribute("loginDTO", userLoginDTO);
        redirectAttributes
                .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
        redirectAttributes
                .addFlashAttribute("userNotFound", true);
        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String logout(){
        userService.logout(user);
        return "redirect:/login";
    }
}
