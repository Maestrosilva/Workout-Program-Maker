package com.example.WorkoutProgramMaker.controllers;

import com.example.WorkoutProgramMaker.model.dto.SessionUser;
import com.example.WorkoutProgramMaker.model.dto.WorkoutDTO;
import com.example.WorkoutProgramMaker.model.entity.ExerciseEntity;
import com.example.WorkoutProgramMaker.model.entity.UserEntity;
import com.example.WorkoutProgramMaker.service.ExerciseService;
import com.example.WorkoutProgramMaker.service.UserService;
import com.example.WorkoutProgramMaker.service.WorkoutService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class WorkoutController {
    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final SessionUser user;
    private final HttpServletRequest request;
    private final Gson gson;

    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService, ModelMapper modelMapper, UserService userService, SessionUser user, HttpServletRequest request, Gson gson) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.user = user;
        this.request = request;
        this.gson = gson;
    }

    @GetMapping("/workout/{workoutName}")
    public String workoutGet(@PathVariable String workoutName,
                             Model model) {
        WorkoutDTO workoutDTO = workoutService.findByUserAndName(user.getUsername(), workoutName);
        Set<ExerciseEntity> exercises = workoutDTO.getExercises();
        String day = "";
        LocalDate date = null;

        date = Calendar.getInstance()
                .getTime()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        day = date.getDayOfWeek().name();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day);
        exercises = exercises.stream()
                .filter(e -> e.getDay().equals(dayOfWeek))
                .sorted(Comparator.comparing(ExerciseEntity::getNumber))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        model.addAttribute("name", workoutName);
        model.addAttribute("day", day);
        model.addAttribute("isLogged", true);
        model.addAttribute("exercises", exercises);
        model.addAttribute("date", date);
        return "workout";
    }

    @GetMapping("/workout/{workoutName}/{dateStr}")
    public String workoutGet(@PathVariable String workoutName,
                             @PathVariable String dateStr,
                             Model model) {
        WorkoutDTO workoutDTO = workoutService.findByUserAndName(user.getUsername(), workoutName);
        Set<ExerciseEntity> exercises = workoutDTO.getExercises();
        String day = "";
        System.out.println(dateStr);
        LocalDate date = LocalDate.parse(dateStr);
        day = date.getDayOfWeek().name();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day);
        exercises = exercises.stream()
                .filter(e -> e.getDay().equals(dayOfWeek))
                .sorted(Comparator.comparing(ExerciseEntity::getNumber))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        model.addAttribute("name", workoutName);
        model.addAttribute("day", day);
        model.addAttribute("isLogged", true);
        model.addAttribute("exercises", exercises);
        model.addAttribute("date", date);
        return "workout";
    }

    @PostMapping("/workout/{workoutName}")
    public String workoutPost(@PathVariable(required = false) String workoutName,
                              Model model) {
        model.addAttribute("isLogged", true);
        LocalDate date;
        try {
            date = LocalDate.parse(request.getParameter("date"));
        } catch (Exception e) {
            date = Calendar.getInstance()
                    .getTime()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        String day = String.valueOf(date.getDayOfWeek());
        WorkoutDTO workoutDTO = workoutService.findByUserAndName(user.getUsername(), workoutName);
        Set<ExerciseEntity> exercises = workoutDTO.getExercises();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day);
        exercises = exercises.stream()
                .filter(e -> e.getDay().equals(dayOfWeek))
                .sorted(Comparator.comparing(ExerciseEntity::getNumber))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        try {
            double[] kg = Arrays.stream(request.getParameterValues("kg"))
                    .mapToDouble(Double::parseDouble).toArray();
            int[] reps = Arrays.stream(request.getParameterValues("reps"))
                    .mapToInt(Integer::parseInt).toArray();
            int[] sets = Arrays.stream(request.getParameterValues("sets"))
                    .mapToInt(Integer::parseInt).toArray();
            List<ExerciseEntity> exerciseList = exercises.stream().toList();
            for (int i = 0; i < exerciseList.size(); i++) {
                ExerciseEntity exercise = exerciseList.get(i);
                exercise.setKg(kg[i]);
                exercise.setReps(reps[i]);
                exercise.setNumberOfSets(sets[i]);
            }
            exercises = new LinkedHashSet<>(exerciseList);

        } catch (Exception ignored) {
        }
        return "redirect:/workout/" + workoutName + "/" + date;
    }

    @PostMapping("/workout/remove/{workout}")
    public String workoutRemove(@PathVariable String workout) {
        WorkoutDTO workoutDTO = workoutService.findByUserAndName(user.getUsername(), workout);
        Set<ExerciseEntity> exercises = workoutDTO.getExercises();
        UserEntity currentUser = userService.getUserByUsername(user.getUsername());
        exercises.forEach(e -> currentUser.getOldExercises().add(e));
        exercises.forEach(e -> e.setWorkout(null));
        workoutService.delete(user.getUsername(), workoutDTO);
        workoutDTO.setUser(userService.getUserByUsername(user.getUsername()));
        return "redirect:/home";
    }

    @PostMapping("/exercise/edit/remove/{workout}/{exercise}")
    public String exerciseEditRemove(@PathVariable String workout, @PathVariable Long exercise
            , Model model) {
        ExerciseEntity currentExercise = exerciseService.getExerciseById(exercise);
        workoutService.removeExerciseFromWorkoutWithName(user.getUsername(), currentExercise, workout);
        exerciseService.remove(currentExercise);
        UserEntity currentUser = userService.getUserByUsername(user.getUsername());
//        currentUser.getOldExercises().add(currentExercise);
        
        return "redirect:/workout/edit/" + workout;
    }

    @PostMapping("workout/edit/{workout}/{dateStr}")
    public String workoutEditPost(@PathVariable String workout,
                                  @PathVariable String dateStr,
                                  Model model) {
        WorkoutDTO workoutDTO = workoutService.findByUserAndName(user.getUsername(), workout);
        Set<ExerciseEntity> exercises = workoutDTO.getExercises();
        LocalDate date = null;
        try {
            date = LocalDate.parse(request.getParameter("date"));
        } catch (Exception e) {
            date = LocalDate.parse(dateStr);
        }
        String day = String.valueOf(date.getDayOfWeek());
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day);
        exercises = exercises.stream()
                .filter(e -> e.getDay().equals(dayOfWeek))
                .sorted(Comparator.comparing(ExerciseEntity::getNumber))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return "redirect:/workout/edit/" + workout + "/" + date;
    }

    @PostMapping("workout/edit/{workout}")
    public String workoutEditPost(@PathVariable String workout,
                                  Model model) {
        WorkoutDTO workoutDTO = workoutService.findByUserAndName(user.getUsername(), workout);
        Set<ExerciseEntity> exercises = workoutDTO.getExercises();
        LocalDate date = null;
        try {
            date = LocalDate.parse(request.getParameter("date"));
        } catch (Exception ignored) {
        }
        String day = String.valueOf(date.getDayOfWeek());
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day);
        exercises = exercises.stream()
                .filter(e -> e.getDay().equals(dayOfWeek))
                .sorted(Comparator.comparing(ExerciseEntity::getNumber))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return "redirect:/workout/edit/" + workout + "/" + date;
    }

    @GetMapping("workout/edit/{workout}/{dateStr}")
    public String workoutEditGet(@PathVariable String workout,
                                 @PathVariable String dateStr,
                                 Model model) {
        WorkoutDTO workoutDTO = workoutService.findByUserAndName(user.getUsername(), workout);
        Set<ExerciseEntity> exercises = workoutDTO.getExercises();
        LocalDate date = LocalDate.parse(dateStr);
        String day = date.getDayOfWeek().name();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day);
        exercises = exercises.stream()
                .filter(e -> e.getDay().equals(dayOfWeek))
                .sorted(Comparator.comparing(ExerciseEntity::getNumber))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        model.addAttribute("isLogged", true);
        model.addAttribute("name", workout);
        model.addAttribute("day", day);
        model.addAttribute("exercises", exercises);
        model.addAttribute("date", date);
        return "edit";
    }
}
