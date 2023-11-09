package com.eoisaac.wod.database.repositories

import android.content.Context
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.models.User

class UserRepository(context: Context) {
    private val appDatabase = AppDatabase.getDatabase(context).userDao()

    fun insert(user: User): Long {
        return appDatabase.insert(user)
    }

    fun update(user: User): Int {
        return appDatabase.update(user)
    }

    fun delete(user: User): Int {
        return appDatabase.delete(user)
    }

    fun getById(id: Long): User {
        return appDatabase.getById(id)
    }

    fun getByEmail(email: String): User {
        return appDatabase.getByEmail(email)
    }

    fun getAll(): List<User> {
        return appDatabase.getAll()
    }
}