package com.example.WorkoutProgramMaker.controllers;

import com.example.WorkoutProgramMaker.model.dto.SessionUser;
import com.example.WorkoutProgramMaker.model.dto.WorkoutDTO;
import com.example.WorkoutProgramMaker.model.entity.ExerciseEntity;
import com.example.WorkoutProgramMaker.model.entity.UserEntity;
import com.example.WorkoutProgramMaker.model.entity.WorkoutEntity;
import com.example.WorkoutProgramMaker.model.entity.enums.Category;
import com.example.WorkoutProgramMaker.model.entity.enums.Day;
import com.example.WorkoutProgramMaker.model.entity.enums.ExerciseType;
import com.example.WorkoutProgramMaker.model.entity.enums.MuscleGroup;
import com.example.WorkoutProgramMaker.service.ExerciseService;
import com.example.WorkoutProgramMaker.service.UserService;
import com.example.WorkoutProgramMaker.service.WorkoutService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

@Controller
public class HomeController {
    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final SessionUser user;
    private final HttpServletRequest request;
    private final Gson gson;

    @ModelAttribute("currentWorkout")
    public Set<Object> currentWorkout() {
        return new LinkedHashSet<>();
    }


    public HomeController(WorkoutService workoutService, ExerciseService exerciseService, ModelMapper modelMapper, UserService userService, SessionUser user, HttpServletRequest request, Gson gson) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.user = user;
        this.request = request;
        this.gson = gson;
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if (user.getID() == null) {
            return "redirect:/login";
        }
        UserEntity userEntity = userService.getByID(user.getID());
        Set<WorkoutDTO> workouts = workoutService.getAllWorkouts(userEntity);
        ExerciseType[] exercises = Arrays.stream(ExerciseType.values()).sorted(Comparator.comparing(ExerciseType::name)).toArray(ExerciseType[]::new);
        Category[] categories = Category.values();
        MuscleGroup[] muscleGroups = Arrays.stream(MuscleGroup.values()).sorted(Comparator.comparing(Enum::name)).toArray(MuscleGroup[]::new);
        Day[] days = Day.values();
        model.addAttribute("isLogged", true);
        model.addAttribute("workouts", workouts);
        model.addAttribute("exercises", exercises);
        model.addAttribute("categories", categories);
        model.addAttribute("muscleGroups", muscleGroups);
        model.addAttribute("current", "");
        model.addAttribute("days", days);
        return "home-page";
    }

    @PostMapping("/home")
    public String postHome(Model model) {
        UserEntity userEntity = userService.getByID(user.getID());
        Set<WorkoutDTO> workouts = workoutService.getAllWorkouts(userEntity);
        model.addAttribute("workouts", workouts);
        return "home-page";
    }

    @PostMapping("/home/create")
    public String create(Model model, HttpServletRequest request) {
        String data = request.getParameter("current");
        String name = request.getParameter("name");
        Set<ExerciseEntity> exercises = exerciseService.getExerciseList(data);
        WorkoutEntity workout = new WorkoutEntity();
        workout.setUser(userService.getByID(user.getID()));
        if(name.isBlank()){
            name = "NO NAME";
        }
        int i = 1;
        String counter = "";
        while (workoutService.findByUserAndName(user.getUsername(), name + counter) != null) {
            counter = String.valueOf(i);
            i++;
        }
        name = name + counter;
        workout.setName(name);
        workoutService.save(workout);
        workout.setExercises(exercises);
        for (ExerciseEntity ex : exercises) {
            exerciseService.setWorkout(ex, workout);
            exerciseService.save(ex);
        }
        System.out.println(data);
        return "redirect:/home";
    }
}
