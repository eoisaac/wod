package com.eoisaac.wod.database.dao

import androidx.room.*
import com.eoisaac.wod.database.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User): Int

    @Delete
    suspend fun delete(user: User): Int

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getById(id: Long): User

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getByEmail(email: String): User

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>
}