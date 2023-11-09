package com.eoisaac.wod.database.dao

import androidx.room.*
import com.eoisaac.wod.database.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Update
    fun update(user: User): Int

    @Delete
    fun delete(user: User): Int

    @Query("SELECT * FROM users WHERE id = :id")
    fun getById(id: Long): User

    @Query("SELECT * FROM users WHERE email = :email")
    fun getByEmail(email: String): User

    @Query("SELECT * FROM users")
    fun getAll(): List<User>
}