package com.eoisaac.wod.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eoisaac.wod.database.dao.ExerciseDao
import com.eoisaac.wod.database.dao.WorkoutDao
import com.eoisaac.wod.database.dao.WorkoutHasWeekDayDao
import com.eoisaac.wod.database.models.Exercise
import com.eoisaac.wod.database.models.Workout
import com.eoisaac.wod.database.models.WorkoutHasWeekDay

/**
 * Room database class
 * Contains the database holder and serves as the main access point for the underlying connection to your app's
 * persisted, relational data.
 */
@Database(
    entities = [
        Exercise::class,
        Workout::class,
        WorkoutHasWeekDay::class,
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutHasWeekDayDao(): WorkoutHasWeekDayDao

    companion object {
        private lateinit var INSTANCE: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(AppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "wod_database",
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}