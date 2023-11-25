package com.eoisaac.wod.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eoisaac.wod.database.models.Workout
import com.eoisaac.wod.database.models.WorkoutWithExercises

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workout: Workout): Long

    @Delete
    fun delete(workout: Workout): Int

    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getById(id: Long): Workout

    @Query("SELECT * FROM workouts WHERE :weekDay IN (week_days)")
    fun getByWeekDay(weekDay: String): Workout

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :id")
    fun getWorkoutWithExercisesById(id: Long): WorkoutWithExercises

    // TODO: fix this
    @Transaction
    @Query("SELECT * FROM workouts WHERE :weekDay IN (week_days)")
    fun getWorkoutsWithExercisesByWeekDay(weekDay: String): LiveData<List<WorkoutWithExercises>>

    @Transaction
    @Query("SELECT * FROM workouts")
    fun getWorkoutsWithExercises(): LiveData<List<WorkoutWithExercises>>
}