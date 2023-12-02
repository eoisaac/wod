package com.eoisaac.wod.interfaces

import com.eoisaac.wod.database.models.Exercise

interface ExercisePressListener {
    fun onCheckPress(exercise: Exercise)
}