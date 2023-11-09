package com.eoisaac.wod.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.dao.UserDao
import com.eoisaac.wod.database.models.User
import com.eoisaac.wod.database.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignUpModelView(application: Application) : AndroidViewModel(application) {

    private val userDao: UserDao
    private val userRepository: UserRepository

    init {
        val database = AppDatabase.getDatabase(application)
        userDao = database.userDao()
        userRepository = UserRepository(userDao)
    }


    fun signUpUser(name: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.getByEmail(email)
            if (user != null) {
                println("User already exists")
                return@launch
            }
            if (password != confirmPassword) {
                println("Passwords do not match")
                return@launch
            }

            val newUser = User(0, name, email, password)
            userRepository.insert(newUser)
        }
    }
}