package com.eoisaac.wod.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eoisaac.wod.database.dao.ExerciseDao
import com.eoisaac.wod.database.dao.WorkoutDao
import com.eoisaac.wod.database.models.Exercise
import com.eoisaac.wod.database.models.Workout

@Database(
    entities = [
        Exercise::class,
        Workout::class,
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao

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