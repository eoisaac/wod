package com.eoisaac.wod.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eoisaac.wod.R
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.database.repositories.WorkoutRepository
import com.eoisaac.wod.utils.StringContent
import java.util.Calendar
import java.util.Date


class WorkoutViewModel(app: Application) : AndroidViewModel(app) {
    private val workoutRepository: WorkoutRepository
    private val exerciseRepository: ExerciseRepository

    init {
        val database = AppDatabase.getDatabase(app)

        val workoutDao = database.workoutDao()
        workoutRepository = WorkoutRepository(workoutDao)

        val exerciseDao = database.exerciseDao()
        exerciseRepository = ExerciseRepository(exerciseDao)
    }

    private var greeting = MutableLiveData<StringContent>()
    fun timeBasedGreeting(): LiveData<StringContent> {
        return greeting
    }

    fun getTimeBasedGreeting(date: Date = Date()) {
        val calendar = Calendar.getInstance()
        calendar.time = date

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val greetingResourceId = when (hour) {
            in 6..11 -> R.string.morning_greeting
            in 12..17 -> R.string.afternoon_greeting
            else -> R.string.evening_greeting
        }

        this.greeting.value = StringContent.StringResource(resourceId = greetingResourceId)
    }
}