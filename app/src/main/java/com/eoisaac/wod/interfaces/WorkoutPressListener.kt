package com.eoisaac.wod.interfaces

import com.eoisaac.wod.database.models.WorkoutWithExercises

/**
 * Interface to handle the delete press event on a workout
 */
interface WorkoutPressListener {
    fun onDeletePress(workout: WorkoutWithExercises)
}