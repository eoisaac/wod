package com.eoisaac.wod.database.dao

import androidx.room.*
import com.eoisaac.wod.database.models.Exercise

@Dao
interface ExerciseDao {

    /**
     * Get all exercises
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercise: Exercise): Long

    /**
     * Update an exercise
     */
    @Update
    fun update(exercise: Exercise): Int


    /**
     * Update all exercises with the given [isCompleted] value to reset them
     */
    @Query("UPDATE exercises SET is_completed = :isCompleted")
    fun resetAll(isCompleted: Boolean): Int
}