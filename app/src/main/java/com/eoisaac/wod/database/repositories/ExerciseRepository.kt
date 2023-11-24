package com.eoisaac.wod.database.repositories

import com.eoisaac.wod.database.dao.ExerciseDao
import com.eoisaac.wod.database.models.Exercise

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    fun insert(exercise: Exercise): Long {
        return exerciseDao.insert(exercise)
    }

    fun update(exercise: Exercise): Int {
        return exerciseDao.update(exercise)
    }

    fun delete(exercise: Exercise): Int {
        return exerciseDao.delete(exercise)
    }

    fun getById(id: Long): Exercise {
        return exerciseDao.getById(id)
    }

    fun getByWorkoutId(workoutId: Long): List<Exercise> {
        return exerciseDao.getByWorkoutId(workoutId)
    }
}