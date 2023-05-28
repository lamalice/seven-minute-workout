package com.example.sevenminuteworkout

object Constants {

    fun defaultExerciseList (): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks = ExerciseModel(
            id = 1,
            name = "Jumping Jacks",
            image = R.drawable.ic_jumping_jacks
        )
        exerciseList.add(jumpingJacks)

        val abCrunch = ExerciseModel(
            id = 2,
            name = "Abdominal Crunches",
            image = R.drawable.ic_abdominal_crunch
        )
        exerciseList.add(abCrunch)

        val highKneesRun = ExerciseModel(
            id = 3,
            name = "High Knees Running",
            image = R.drawable.ic_high_knees_running_in_place
        )
        exerciseList.add(highKneesRun)

        val lunge = ExerciseModel(
            id = 4,
            name = "Lunges",
            image = R.drawable.ic_lunge
        )
        exerciseList.add(lunge)

        val plank = ExerciseModel(
            id = 5,
            name = "Planks",
            image = R.drawable.ic_plank
        )
        exerciseList.add(plank)

        val pushUp = ExerciseModel(
            id = 6,
            name = "Push Ups",
            image = R.drawable.ic_push_up
        )
        exerciseList.add(pushUp)

        val pushUpRot = ExerciseModel(
            id = 7,
            name = "Push Up Rotations",
            image = R.drawable.ic_push_up_and_rotation
        )
        exerciseList.add(pushUpRot)

        val sidePlank = ExerciseModel(
            id = 8,
            name = "Side Planks",
            image = R.drawable.ic_side_plank
        )
        exerciseList.add(sidePlank)

        val squat = ExerciseModel(
            id = 9,
            name = "Squats",
            image = R.drawable.ic_squat
        )
        exerciseList.add(squat)

        val stepUp = ExerciseModel(
            id = 10,
            name = "Step Up Onto Chair",
            image = R.drawable.ic_step_up_onto_chair
        )
        exerciseList.add(stepUp)

        val tricepDip = ExerciseModel(
            id = 11,
            name = "Triceps Dip Onto Chair",
            image = R.drawable.ic_triceps_dip_on_chair
        )
        exerciseList.add(tricepDip)

        val wallSit = ExerciseModel(
            id = 12,
            name = "Wall Sit",
            image = R.drawable.ic_wall_sit
        )
        exerciseList.add(wallSit)

        return exerciseList
    }
}