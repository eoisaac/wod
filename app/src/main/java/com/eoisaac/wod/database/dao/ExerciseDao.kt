package com.eoisaac.wod.database.dao

import androidx.room.*
import com.eoisaac.wod.database.models.Exercise

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercise: Exercise): Long

    @Update
    fun update(exercise: Exercise): Int

    @Delete
    fun delete(exercise: Exercise): Int

    @Query("SELECT * FROM exercises WHERE id = :id")
    fun getById(id: Long): Exercise

    @Query("SELECT * FROM exercises WHERE workout_id = :workoutId")
    fun getByWorkoutId(workoutId: Long): List<Exercise>

}