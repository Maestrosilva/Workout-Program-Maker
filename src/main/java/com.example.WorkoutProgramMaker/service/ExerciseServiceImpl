package com.example.WorkoutProgramMaker.service;

import com.example.WorkoutProgramMaker.model.dto.ExerciseDTO;
import com.example.WorkoutProgramMaker.model.entity.ExerciseEntity;
import com.example.WorkoutProgramMaker.model.entity.WorkoutEntity;
import com.example.WorkoutProgramMaker.model.entity.enums.ExerciseType;
import com.example.WorkoutProgramMaker.repository.ExerciseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ModelMapper modelMapper;
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ModelMapper modelMapper, ExerciseRepository exerciseRepository) {
        this.modelMapper = modelMapper;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Set<ExerciseEntity> getExerciseList(String data) {
        Set<ExerciseEntity> exercises = new LinkedHashSet<>();
        String[] ex = data.replace("\"", "")
                .replace("\\", "")
                .replace("[", "")
                .replace("]", "")
                .replace("{", "")
                .replace("}", "")
                .split(",");
        for (int i = 0; i < ex.length - 1; i += 6) {
            String exType = ex[i].split(":")[1].toUpperCase().replace(" ", "_");
            int sets = Integer.parseInt(ex[i + 1].split(":")[1]);
            double kg = Double.parseDouble(ex[i + 2].split(":")[1]);
            int reps = Integer.parseInt(ex[i + 3].split(":")[1]);
            int num = Integer.parseInt(ex[i + 4].split(":")[1]);
            String day = ex[i + 5].split(":")[1];
            ExerciseDTO exerciseDTO = new ExerciseDTO();
            exerciseDTO.setExerciseType(ExerciseType.valueOf(exType));
            exerciseDTO.setKg(kg);
            exerciseDTO.setReps(reps);
            exerciseDTO.setNumberOfSets(sets);
            exerciseDTO.setWorkoutDay(DayOfWeek.valueOf(day));
            exerciseDTO.setNumber(num);
            ExerciseEntity exercise = modelMapper.map(exerciseDTO, ExerciseEntity.class);
            exercises.add(exercise);
        }
        return exercises;
    }

    @Override
    public void setWorkout(ExerciseEntity exercise, WorkoutEntity workout) {
        exercise.setWorkout(workout);
    }

    @Override
    public void save(ExerciseEntity exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public ExerciseEntity getExerciseById(Long exercise) {
        ExerciseEntity exerciseEntity = exerciseRepository.getById(exercise);
        return exerciseEntity;
    }

    @Override
    public void remove(ExerciseEntity currentExercise) {
        exerciseRepository.delete(currentExercise);
    }
}
