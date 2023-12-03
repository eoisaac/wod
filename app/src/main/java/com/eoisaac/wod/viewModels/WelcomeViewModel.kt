package com.eoisaac.wod.viewModels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.repositories.ExerciseRepository
import com.eoisaac.wod.utils.DateUtils
import com.eoisaac.wod.utils.PrefsUtils
import java.util.*

class WelcomeViewModel(app: Application) : AndroidViewModel(app) {

    /**
     * Save the user's name to shared preferences
     */
    fun saveUserName(name: String): Boolean {
        PrefsUtils["user_name"] = name
        return true
    }

    /**
     * Get the user's name from shared preferences
     */
    fun getUserName(): String {
        return PrefsUtils["user_name", ""]
    }

}