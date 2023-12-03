package com.eoisaac.wod.interfaces

import com.eoisaac.wod.database.models.Exercise

/**
 * Interface to handle the check press event on an exercise
 */
interface ExercisePressListener {
    fun onCheckPress(exercise: Exercise)
}