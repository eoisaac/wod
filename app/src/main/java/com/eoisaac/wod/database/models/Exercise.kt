package com.eoisaac.wod.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sets") val sets: Int,
    @ColumnInfo(name = "completed") val completed: Boolean = false,

    @ColumnInfo(name = "workout_id") var workoutId: Long = 0L,
)
