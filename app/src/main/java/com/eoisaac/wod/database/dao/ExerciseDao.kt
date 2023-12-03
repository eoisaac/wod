package com.eoisaac.wod.database.dao

import androidx.room.*
import com.eoisaac.wod.database.models.Exercise

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercise: Exercise): Long

    @Update
    fun update(exercise: Exercise): Int


    @Query("UPDATE exercises SET is_completed = :isCompleted")
    fun resetAll(isCompleted: Boolean): Int
}