package com.eoisaac.wod.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.models.Workout
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.database.repositories.WorkoutRepository

class NewWorkoutViewModel(app: Application) : AndroidViewModel(app) {
    private val workoutRepository: WorkoutRepository
    private val exerciseRepository: ExerciseRepository

    init {
        val database = AppDatabase.getDatabase(app)

        val workoutDao = database.workoutDao()
        workoutRepository = WorkoutRepository(workoutDao)

        val exerciseDao = database.exerciseDao()
        exerciseRepository = ExerciseRepository(exerciseDao)
    }

    fun createNewWorkout(name: String, weekDay: Int) {
        val workout = Workout(name = name, weekDay = weekDay)
        val workoutId = workoutRepository.insert(workout)

        Toast.makeText(getApplication(), "Workout created with id: $workoutId", Toast.LENGTH_SHORT).show()
    }
}