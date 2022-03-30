package com.example.kolizey.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kolizey.model.BookedTour
import com.example.kolizey.model.HistoryTour
import com.example.kolizey.model.Tour
import com.example.kolizey.model.User

@Dao
interface TourBookedDao {

    @Query("SELECT * FROM BookedTour WHERE userId=:id ")
    fun getTours(id: String): List<BookedTour>

    @Query("SELECT * FROM BookedTour WHERE userId=:id ")
    fun getToursBooked(id: String): List<BookedTour>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTour(vararg bookedTour: BookedTour)

    @Delete
    fun delete(tour: BookedTour)

    @Query("DELETE FROM BookedTour")
    fun deleteAllTours()

    @Query("SELECT * FROM BookedTour Where hotelName Like :title||'%' & userId=:id")
    fun getByTitle(title: String, id: String): List<BookedTour>
}