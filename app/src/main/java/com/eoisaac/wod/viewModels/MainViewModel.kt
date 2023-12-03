package com.eoisaac.wod.viewModels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.utils.DateUtils
import java.util.*

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val exerciseRepository: ExerciseRepository

    init {
        val database = AppDatabase.getDatabase(app)
        val exerciseDao = database.exerciseDao()

        exerciseRepository = ExerciseRepository(exerciseDao)
    }

    private val SHARED_PREFS_KEY = "com.eoisaac.wod"
    private val LAST_RESET_KEY = "last_reset"

    fun validateReset() {
        val sharedPrefs = getApplication<Application>().getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
        val lastReset = sharedPrefs.getString(LAST_RESET_KEY, "") ?: ""

        val todayDate = Date()
        val currentWeekDay = DateUtils.getDateWeekDay(todayDate)?.day.toString()

        if (lastReset.isEmpty()|| lastReset != currentWeekDay) {
            Log.i("MainViewModel", "Resetting exercises")
            resetAllExercises()
            sharedPrefs.edit().putString(LAST_RESET_KEY, currentWeekDay).apply()
        }
    }

    private fun resetAllExercises() {
        exerciseRepository.resetAll(false)
    }
}