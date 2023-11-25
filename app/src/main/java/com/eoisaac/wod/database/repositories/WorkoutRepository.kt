package com.eoisaac.wod.database.repositories

import androidx.lifecycle.LiveData
import com.eoisaac.wod.database.dao.WorkoutDao
import com.eoisaac.wod.database.models.Workout
import com.eoisaac.wod.database.models.WorkoutWithExercises

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    fun insert(workout: Workout): Long {
        return workoutDao.insert(workout)
    }

    fun delete(workout: Workout): Int {
        return workoutDao.delete(workout)
    }

    fun getById(id: Long): Workout {
        return workoutDao.getById(id)
    }

    fun getByWeekDay(weekDay: Int): Workout {
        return workoutDao.getByWeekDay(weekDay)
    }

    fun getWorkoutWithExercisesById(id: Long): WorkoutWithExercises {
        return workoutDao.getWorkoutWithExercisesById(id)
    }

    fun getWorkoutsWithExercisesByWeekDay(weekDay: Int): LiveData<List<WorkoutWithExercises>> {
        return workoutDao.getWorkoutsWithExercisesByWeekDay(weekDay)
    }

    fun getAllWorkoutsWithExercises(): LiveData<List<WorkoutWithExercises>> {
        return workoutDao.getWorkoutsWithExercises()
    }
}