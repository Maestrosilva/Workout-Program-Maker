package com.example.WorkoutProgramMaker.service;

import com.example.WorkoutProgramMaker.model.dto.WorkoutDTO;
import com.example.WorkoutProgramMaker.model.entity.ExerciseEntity;
import com.example.WorkoutProgramMaker.model.entity.UserEntity;
import com.example.WorkoutProgramMaker.model.entity.WorkoutEntity;
import com.example.WorkoutProgramMaker.repository.WorkoutRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final ModelMapper modelMapper;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, ModelMapper modelMapper) {
        this.workoutRepository = workoutRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(WorkoutDTO workoutDTO) {
        WorkoutEntity workout = new WorkoutEntity();
        workoutRepository.save(workout);
    }

    @Override
    public void save(WorkoutEntity workoutDTO) {
        workoutRepository.save(workoutDTO);
    }

    @Override
    public Set<WorkoutDTO> getAllWorkouts(UserEntity user) {
        return workoutRepository.getByUser(user)
                .stream()
                .map(w -> modelMapper.map(w, WorkoutDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public WorkoutDTO findByUserAndName(String username, String workoutName) {
         WorkoutEntity workout =  workoutRepository.getByUserAndName(username, workoutName)
                .stream().findFirst()
                .orElse(null);
         if(workout == null) {
             return null;
         }
         return modelMapper.map(workout, WorkoutDTO.class);
    }

    @Override
    public void delete(String user, WorkoutDTO workoutDTO) {
        String name = workoutDTO.getName();
        WorkoutEntity workout = workoutRepository.getByUserAndName(user, name)
                .stream()
                .findFirst()
                .orElse(null);
//        assert workout != null;
        workoutRepository.delete(workout);

    }

    @Override
    public WorkoutDTO findByExercise(ExerciseEntity currentExercise) {
        return modelMapper.map(currentExercise.getWorkout(), WorkoutDTO.class);
    }

    @Override
    public void removeExerciseFromWorkoutWithName(String username, ExerciseEntity exercise, String workout) {
        WorkoutEntity workoutEntity = workoutRepository.getByUserAndName(username, workout)
                .stream()
                .findFirst()
                .orElse(new WorkoutEntity())
                ;
        workoutEntity.getExercises()
                .remove(exercise);
        for (ExerciseEntity workoutEntityExercise : workoutEntity.getExercises()) {
            System.out.println(workoutEntityExercise.getExerciseType().name());

        }

    }
}
