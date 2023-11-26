package com.eoisaac.wod.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eoisaac.wod.R
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.models.WorkoutWithExercises
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.database.repositories.WorkoutRepository
import com.eoisaac.wod.utils.DateUtils
import com.eoisaac.wod.utils.StringContent
import java.util.Date


class WorkoutViewModel(app: Application) : AndroidViewModel(app) {
    private val workoutRepository: WorkoutRepository
    private val exerciseRepository: ExerciseRepository

    private var dayWorkouts: LiveData<List<WorkoutWithExercises>>

    init {
        val database = AppDatabase.getDatabase(app)

        val workoutDao = database.workoutDao()
        workoutRepository = WorkoutRepository(workoutDao)

        val exerciseDao = database.exerciseDao()
        exerciseRepository = ExerciseRepository(exerciseDao)

        val currentWeekDay = DateUtils.getDateWeekDay(Date())!!
        dayWorkouts = workoutRepository.getWorkoutsWithExercisesByWeekDay2(currentWeekDay)
    }

    fun getDayWorkouts(): LiveData<List<WorkoutWithExercises>> {
        return dayWorkouts
    }

    private var greeting = MutableLiveData<StringContent>()
    fun timeBasedGreeting(): LiveData<StringContent> {
        return greeting
    }

    fun getTimeBasedGreeting(date: Date = Date()) {
        val hour = DateUtils.getDateHour(date)
        val greetingResourceId = when (hour) {
            in 6..11 -> R.string.morning_greeting
            in 12..17 -> R.string.afternoon_greeting
            else -> R.string.evening_greeting
        }

        this.greeting.value = StringContent.StringResource(resourceId = greetingResourceId)
    }
}