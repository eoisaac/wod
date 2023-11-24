package com.eoisaac.wod.database.dao

import androidx.room.*
import com.eoisaac.wod.database.models.Workout

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workout: Workout): Long

    @Delete
    fun delete(workout: Workout): Int

    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getById(id: Long): Workout

    @Query("SELECT * FROM workouts WHERE week_day = :weekDay")
    fun getByWeekDay(weekDay: Int): Workout
}