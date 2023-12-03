package com.eoisaac.wod.database.models

import androidx.room.*

/**
 * A data class representing a workout.
 *
 * @property id The unique identifier of the workout.
 * @property name The name of the workout.
 */
@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "name") val name: String,
)

