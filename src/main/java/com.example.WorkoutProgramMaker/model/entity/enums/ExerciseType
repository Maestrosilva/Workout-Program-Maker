package com.example.WorkoutProgramMaker.model.entity.enums;

import static com.example.WorkoutProgramMaker.model.entity.enums.Category.*;
import static com.example.WorkoutProgramMaker.model.entity.enums.MuscleGroup.*;


public enum ExerciseType {
    BENCH_PRESS(PUSH, CHEST, false, false),
    DUMBBELL_BENCH_PRESS(PUSH, CHEST, false, false),
    FLYES(PUSH, CHEST, false, false),
    PUSH_UPS(PUSH, CHEST, false, true),

    /***  CHEST  ***/

    SQUATS(PUSH, LEGS, false, true),
    DEADLIFT(PUSH, LEGS, false, false),
    HIP_THRUST(PUSH, LEGS, false, false),
    BULGARIAN_SQUAT(PUSH, LEGS, false, true),

    /***  LEGS  ***/

    PULL_UPS(PULL, BACK, false, true),
    CHIN_UPS(PULL, BACK, false, true),
    BENT_OVER_ROWS(PULL, BACK, false, false),

    /***  BACK  ***/

    SHOULDER_PRESS(PUSH, SHOULDERS, false, false),

    /***  SHOULDER  ***/

    DUMBBELL_CURL(PULL, BICEPS, false, false),
    HAMMER_CURL(PULL, BICEPS, false, false),

    /***  BICEPS  ***/

    DIPS(PUSH, TRICEPS, false, true),
    DUMBBELL_TRICEPS_EXTENSION(PUSH, TRICEPS, false, false),
    LYING_DUMBBELL_TRICEPS_EXTENSION(PUSH, TRICEPS, false, false),

    /***  TRICEPS  ***/

    SIT_UPS(PULL, CORE, true, true),
    CRUNCHES(PULL, CORE, true, true),

    /***  CORE  ***/

    WRIST_CURL(PULL, FOREARM, false, false),
    REVERSE_WRIST_CURL(PULL, FOREARM, false, false)

    /***  FOREARM  ***/
    ;
    private final Category category;
    private final MuscleGroup group;
    private final Boolean hasDuration;
    private final Boolean hasBodyWeightForm;
    private final String pictureBackgroundUrl;
    private final String pictureShowUrl;

    ExerciseType(Category category, MuscleGroup group, Boolean hasDuration, Boolean bodyWeightForm) {
        this.category = category;
        this.group = group;
        this.hasDuration = hasDuration;
        this.hasBodyWeightForm = bodyWeightForm;
        this.pictureBackgroundUrl = "/pics/" + this.name().toLowerCase() + "-icon.png";
        this.pictureShowUrl = "/pics/" + this.name().toLowerCase() + ".png";
    }

    public Category getCategory() {
        return category;
    }

    public MuscleGroup getGroup() {
        return group;
    }

    public Boolean getHasDuration() {
        return hasDuration;
    }


    public Boolean getHasBodyWeightForm() {
        return hasBodyWeightForm;
    }

    public String getPictureBackgroundUrl() {
        return pictureBackgroundUrl;
    }

    public String getPictureShowUrl() {
        return pictureShowUrl;
    }
}
