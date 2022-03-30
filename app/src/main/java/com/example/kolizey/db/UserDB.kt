package com.example.kolizey.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kolizey.db.dao.UserDao
import com.example.kolizey.model.User
import com.example.kolizey.utils.Converters

@Database(entities = [User::class], version = 1)
@TypeConverters(Converters::class)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        lateinit var userDb: UserDB
        fun getDb(context: Context): UserDB {
            if (!this::userDb.isInitialized)
                userDb = Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,
                    "tours.db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
            return userDb
        }
    }
}