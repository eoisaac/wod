package com.eoisaac.wod.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.utils.DateUtils
import com.eoisaac.wod.utils.PrefsUtils
import java.util.*

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val exerciseRepository: ExerciseRepository

    init {
        val database = AppDatabase.getDatabase(app)
        val exerciseDao = database.exerciseDao()

        exerciseRepository = ExerciseRepository(exerciseDao)
    }

    fun validateReset() {
        val lastReset = PrefsUtils["last_reset", ""]

        val todayDate = Date()
        val currentWeekDay = DateUtils.getDateWeekDay(todayDate)?.day.toString()

        if (lastReset.isEmpty() || lastReset != currentWeekDay) {
            Log.i("MainViewModel", "Resetting exercises")
            resetAllExercises()
            PrefsUtils["last_reset"] = currentWeekDay
        }
    }

    private fun resetAllExercises() {
        exerciseRepository.resetAll(false)
    }
}