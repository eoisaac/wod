package com.eoisaac.wod.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.models.WorkoutWithExercises
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.database.repositories.WorkoutRepository
import com.eoisaac.wod.entities.WorkoutsSummary
import com.eoisaac.wod.utils.DateUtils
import com.eoisaac.wod.utils.Messages
import com.eoisaac.wod.utils.StringContent
import java.util.Date


class WorkoutViewModel(app: Application) : AndroidViewModel(app) {
    private val workoutRepository: WorkoutRepository
    private val exerciseRepository: ExerciseRepository

    private var greeting = MutableLiveData<StringContent>()
    private var dayWorkouts: LiveData<List<WorkoutWithExercises>>
    private var workoutsSummary = MutableLiveData<WorkoutsSummary>()

    init {
        val database = AppDatabase.getDatabase(app)
        val todayDate = Date()

        val workoutDao = database.workoutDao()
        workoutRepository = WorkoutRepository(workoutDao)

        val exerciseDao = database.exerciseDao()
        exerciseRepository = ExerciseRepository(exerciseDao)

        val currentWeekDay = DateUtils.getDateWeekDay(todayDate)!!
        dayWorkouts = workoutRepository.getWorkoutsWithExercisesByWeekDay(currentWeekDay)
        greeting.value = Messages.getTimeBasedGreeting(todayDate)

        dayWorkouts.observeForever { workouts -> calculateWorkoutsSummary(workouts) }
    }

    fun getDayWorkouts(): LiveData<List<WorkoutWithExercises>> {
        return dayWorkouts
    }

    fun getTimeBasedGreeting(): LiveData<StringContent> {
        return greeting
    }

    fun getWorkoutsSummary(): LiveData<WorkoutsSummary> {
        return workoutsSummary
    }

    private fun calculateWorkoutsSummary(workouts: List<WorkoutWithExercises>) {
        val totalExercises = workouts.sumOf { it.exercises.size }
        val totalCompletedExercises = workouts.sumOf { workout -> workout.exercises.count { it.completed } }
        val completedPercentage = (totalCompletedExercises.toDouble() / totalExercises.toDouble() * 100).toInt()

        workoutsSummary.value = WorkoutsSummary(
            totalExercises = totalExercises,
            totalCompletedExercises = totalCompletedExercises,
            completedPercentage = completedPercentage,
        )
    }
}