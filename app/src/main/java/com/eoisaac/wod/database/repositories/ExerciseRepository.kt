package com.eoisaac.wod.database.repositories

import com.eoisaac.wod.database.dao.ExerciseDao
import com.eoisaac.wod.database.models.Exercise

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    fun insert(exercise: Exercise): Long {
        return exerciseDao.insert(exercise)
    }

    fun update(exercise: Exercise) {
        exerciseDao.update(exercise)
    }
}