package com.eoisaac.wod.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.models.Exercise
import com.eoisaac.wod.database.models.Workout
import com.eoisaac.wod.database.models.WorkoutHasWeekDay
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.database.repositories.WorkoutHasWeekDayRepository
import com.eoisaac.wod.database.repositories.WorkoutRepository
import com.eoisaac.wod.entities.WeekDays

class NewWorkoutViewModel(app: Application) : AndroidViewModel(app) {
    private val workoutRepository: WorkoutRepository
    private val exerciseRepository: ExerciseRepository
    private val workoutHasWeekDayRepository: WorkoutHasWeekDayRepository

    init {
        val database = AppDatabase.getDatabase(app)

        val workoutDao = database.workoutDao()
        workoutRepository = WorkoutRepository(workoutDao)

        val exerciseDao = database.exerciseDao()
        exerciseRepository = ExerciseRepository(exerciseDao)

        val workoutHasWeekDayDao = database.workoutHasWeekDayDao()
        workoutHasWeekDayRepository = WorkoutHasWeekDayRepository(workoutHasWeekDayDao)
    }

    private val newWorkoutExercises = mutableListOf<Exercise>()
    fun getNewWorkoutExercises(): MutableList<Exercise> = newWorkoutExercises

    fun addExercise(name: String, sets: Int) {
        val exercise = Exercise(name = name, sets = sets)
        newWorkoutExercises.add(exercise)
    }

    fun createNewWorkout(name: String, weekDays: List<WeekDays>): Long {
        val weekDaysList = weekDays.map { it.name }
        val workout = Workout(name = name)

        val newWorkoutId = workoutRepository.insert(workout)

        weekDaysList.forEach { weekDay ->
            val workoutHasWeekDay = WorkoutHasWeekDay(workoutId = newWorkoutId, weekDay = weekDay)
            workoutHasWeekDayRepository.insert(workoutHasWeekDay)
            Log.d("NewWorkoutHasWeekDay", workoutHasWeekDay.toString())
        }

        newWorkoutExercises.forEach { exercise ->
            exercise.workoutId = newWorkoutId
            exerciseRepository.insert(exercise)
            Log.d("NewExercise", exercise.toString())
        }

        Log.d("NewWorkout", workout.toString())
        return newWorkoutId
    }
}