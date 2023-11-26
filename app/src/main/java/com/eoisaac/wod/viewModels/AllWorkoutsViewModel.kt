package com.eoisaac.wod.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.models.WorkoutWithExercises
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.database.repositories.WorkoutRepository
import kotlinx.coroutines.launch


class AllWorkoutsViewModel(app: Application) : AndroidViewModel(app) {
    private val workoutRepository: WorkoutRepository
    private val exerciseRepository: ExerciseRepository

    private var allWorkouts: LiveData<List<WorkoutWithExercises>>

    init {
        val database = AppDatabase.getDatabase(app)

        val workoutDao = database.workoutDao()
        workoutRepository = WorkoutRepository(workoutDao)

        val exerciseDao = database.exerciseDao()
        exerciseRepository = ExerciseRepository(exerciseDao)

        allWorkouts = workoutRepository.getAllWorkoutsWithExercises()
    }

    fun getAllWorkouts(): LiveData<List<WorkoutWithExercises>> {
        return allWorkouts
    }

    fun deleteWorkout(workout: WorkoutWithExercises) {
        workoutRepository.delete(workout.workout)
    }
}