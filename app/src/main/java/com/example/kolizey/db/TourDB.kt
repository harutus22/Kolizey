package com.example.kolizey.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kolizey.db.dao.TourDao
import com.example.kolizey.model.Tour
import com.example.kolizey.model.User
import com.example.kolizey.utils.Converters

@Database(entities = [Tour::class, User::class], version = 1)
@TypeConverters(Converters::class)
abstract class TourDB : RoomDatabase() {
    abstract fun taskDao(): TourDao

    companion object {
        lateinit var tourDb: TourDB
        fun getDb(context: Context): TourDB {
            if (!this::tourDb.isInitialized)
                tourDb = Room.databaseBuilder(
                    context.applicationContext,
                    TourDB::class.java,
                    "tours.db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
            return tourDb
        }
    }
}