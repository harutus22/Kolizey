package com.example.kolizey.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kolizey.model.Tour
import com.example.kolizey.model.User

@Dao
interface TourDao {

    @Query("SELECT * FROM Tour")
    fun getTours(): LiveData<List<Tour>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTour(vararg numberReturnModel: Tour)

    @Delete
    fun delete(tour: Tour)

    @Query("DELETE FROM Tour")
    fun deleteAllTours()

    @Query("SELECT * FROM User WHERE id=:id ")
    fun loadUser(id: String): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)
}