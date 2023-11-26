package com.eoisaac.wod.interfaces

import com.eoisaac.wod.database.models.WorkoutWithExercises

interface WorkoutPressListener {
    fun onDeletePress(workout: WorkoutWithExercises)
//    fun onCheckPress(workout: WorkoutWithExercises)
}