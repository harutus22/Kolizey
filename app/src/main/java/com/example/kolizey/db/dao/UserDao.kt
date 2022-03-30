package com.example.kolizey.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kolizey.model.Tour
import com.example.kolizey.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User WHERE id=:id ")
    fun loadUser(id: String): User

    @Query("SELECT * FROM User WHERE telephone=:number")
    fun checkUser(number: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User): Long

    @Update
    fun updateUser(user: User)
}