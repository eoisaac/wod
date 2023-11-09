package com.eoisaac.wod.database.repositories

import android.content.Context
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.dao.UserDao
import com.eoisaac.wod.database.models.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insert(user: User): Long {
        return userDao.insert(user)
    }

    suspend fun update(user: User): Int {
        return userDao.update(user)
    }

    suspend fun delete(user: User): Int {
        return userDao.delete(user)
    }

    suspend fun getById(id: Long): User {
        return userDao.getById(id)
    }

    suspend fun getByEmail(email: String): User {
        return userDao.getByEmail(email)
    }

    suspend fun getAll(): List<User> {
        return userDao.getAll()
    }
}