package com.example.kolizey.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kolizey.db.dao.TourDao
import com.example.kolizey.db.dao.TourHistoryDao
import com.example.kolizey.model.HistoryTour
import com.example.kolizey.model.Tour
import com.example.kolizey.model.User
import com.example.kolizey.utils.Converters

@Database(entities = [HistoryTour::class], version = 1)
@TypeConverters(Converters::class)
abstract class TourHistoryDB : RoomDatabase() {
    abstract fun taskDao(): TourHistoryDao

    companion object {
        lateinit var tourDb: TourHistoryDB
        fun getDb(context: Context): TourHistoryDB {
            if (!this::tourDb.isInitialized)
                tourDb = Room.databaseBuilder(
                    context.applicationContext,
                    TourHistoryDB::class.java,
                    "tours_history.db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
            return tourDb
        }
    }
}