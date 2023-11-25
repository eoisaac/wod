package com.eoisaac.wod.viewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.eoisaac.wod.R
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.models.Exercise
import com.eoisaac.wod.database.models.Workout
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.database.repositories.WorkoutRepository
import com.eoisaac.wod.entities.WeekDays

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

    private val newWorkoutExercises = mutableListOf<Exercise>()
    fun getNewWorkoutExercises(): MutableList<Exercise> = newWorkoutExercises

    fun addExercise(name: String, sets: Int) {
        val exercise = Exercise(name = name, sets = sets)
        newWorkoutExercises.add(exercise)
    }

    fun createNewWorkout(name: String, weekDays: List<WeekDays>): Long {
        val weekDay = weekDays[0].index
        val workout = Workout(name = name, weekDay = weekDay)
        return workoutRepository.insert(workout)
    }
}