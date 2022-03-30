package com.example.kolizey.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kolizey.model.HistoryTour
import com.example.kolizey.model.Tour
import com.example.kolizey.model.User

@Dao
interface TourHistoryDao {

    @Query("SELECT * FROM HistoryTour WHERE userId=:id ")
    fun getTours(id: String): LiveData<List<HistoryTour>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTour(vararg numberReturnModel: HistoryTour)

    @Delete
    fun delete(tour: HistoryTour)

    @Query("DELETE FROM HistoryTour")
    fun deleteAllTours()
}