package com.eoisaac.wod.database.repositories

import android.content.Context
import com.eoisaac.wod.database.AppDatabase
import com.eoisaac.wod.database.dao.UserDao
import com.eoisaac.wod.database.models.User

class UserRepository(private val userDao: UserDao) {
    fun insert(user: User): Long {
        return userDao.insert(user)
    }

    fun update(user: User): Int {
        return userDao.update(user)
    }

    fun delete(user: User): Int {
        return userDao.delete(user)
    }

    fun getById(id: Long): User {
        return userDao.getById(id)
    }

    fun getByEmail(email: String): User {
        return userDao.getByEmail(email)
    }

    fun getAll(): List<User> {
        return userDao.getAll()
    }
}