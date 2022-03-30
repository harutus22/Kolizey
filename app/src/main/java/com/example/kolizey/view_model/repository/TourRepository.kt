package com.example.kolizey.view_model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.kolizey.db.TourDB
import com.example.kolizey.db.dao.TourDao
import com.example.kolizey.model.Tour
import kotlinx.coroutines.*

class TourRepository(private val app: Application) {
    companion object{
        private lateinit var tourDao: TourDao
    }
    private lateinit var allTours: LiveData<List<Tour>>

    fun init() {
        val db = TourDB.getDb(app)
        tourDao = db.taskDao()
        allTours = tourDao.getTours()
    }

    fun insert(tour: Tour) {
        CoroutineScope(Dispatchers.IO).launch {
            tourDao.addTour(tour)
        }
    }

    fun getTour(): LiveData<List<Tour>> = tourDao.getTours()

    fun deleteTour(tour: Tour) {
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