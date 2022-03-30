package com.example.kolizey.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kolizey.db.dao.TourBookedDao
import com.example.kolizey.db.dao.TourDao
import com.example.kolizey.db.dao.TourHistoryDao
import com.example.kolizey.model.BookedTour
import com.example.kolizey.model.Tour
import com.example.kolizey.model.User
import com.example.kolizey.utils.Converters

@Database(entities = [BookedTour::class], version = 1)
@TypeConverters(Converters::class)
abstract class TourBookedDB : RoomDatabase() {
    abstract fun taskDao(): TourBookedDao

    companion object {
        lateinit var tourDb: TourBookedDB
        fun getDb(context: Context): TourBookedDB {
            if (!this::tourDb.isInitialized)
                tourDb = Room.databaseBuilder(
                    context.applicationContext,
                    TourBookedDB::class.java,
                    "tours_booked.db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
            return tourDb
        }
    }
}