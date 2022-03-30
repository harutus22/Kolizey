package com.example.kolizey.view_model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.kolizey.db.TourDB
import com.example.kolizey.db.TourHistoryDB
import com.example.kolizey.db.dao.TourDao
import com.example.kolizey.db.dao.TourHistoryDao
import com.example.kolizey.model.HistoryTour
import com.example.kolizey.model.Tour
import kotlinx.coroutines.*

class TourHistoryRepository(private val app: Application) {
    companion object{
        private lateinit var tourDao: TourHistoryDao
    }
    private lateinit var allTours: LiveData<List<HistoryTour>>

    fun init(id: String) {
        val db = TourHistoryDB.getDb(app)
        tourDao = db.taskDao()
        allTours = tourDao.getTours(id)
    }

    fun insert(tour: HistoryTour) {
        CoroutineScope(Dispatchers.IO).launch {
            tourDao.addTour(tour)
        }
    }

    fun getTour(id: String): LiveData<List<HistoryTour>> = tourDao.getTours(id)

    fun deleteTour(tour: HistoryTour) {
        CoroutineScope(Dispatchers.IO).launch {
            tourDao.delete(tour)
        }
    }

    fun deleteAllTours() {
        CoroutineScope(Dispatchers.IO).launch {
            tourDao.deleteAllTours()
        }
    }
}