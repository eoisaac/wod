package com.eoisaac.wod.database.dao

import androidx.room.*
import com.eoisaac.wod.database.models.Exercise

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercise: Exercise): Long

}