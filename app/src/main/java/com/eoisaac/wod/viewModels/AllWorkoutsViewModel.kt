package com.eoisaac.wod.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.database.repositories.WorkoutRepository


class AllWorkoutsViewModel(app: Application) : AndroidViewModel(app) {
    private val workoutRepository: WorkoutRepository
    private val exerciseRepository: ExerciseRepository

    init {
        val database = AppDatabase.getDatabase(app)

        val workoutDao = database.workoutDao()
        workoutRepository = WorkoutRepository(workoutDao)

        val exerciseDao = database.exerciseDao()
        exerciseRepository = ExerciseRepository(exerciseDao)
    }
}