package com.example.kolizey.view_model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kolizey.db.TourBookedDB
import com.example.kolizey.db.TourDB
import com.example.kolizey.db.TourHistoryDB
import com.example.kolizey.db.dao.TourBookedDao
import com.example.kolizey.db.dao.TourDao
import com.example.kolizey.db.dao.TourHistoryDao
import com.example.kolizey.model.BookedTour
import com.example.kolizey.model.HistoryTour
import com.example.kolizey.model.Tour
import kotlinx.coroutines.*

class TourBookedRepository(private val app: Application) {
    companion object{
        private lateinit var tourDao: TourBookedDao
    }
    private lateinit var allTours: LiveData<List<BookedTour>>
    private var bookedTours: MutableLiveData<List<BookedTour>> = MutableLiveData()

    fun init(id: String) {
        val db = TourBookedDB.getDb(app)
        tourDao = db.taskDao()
        bookedTours.value = tourDao.getTours(id)
    }

    fun insert(tour: BookedTour) {
        CoroutineScope(Dispatchers.IO).launch {
            tourDao.addTour(tour)
        }
    }

    fun getTour(id: String): LiveData<List<BookedTour>> = bookedTours

    fun getByHotelName(hotelName: String, id: String){
        bookedTours.value = tourDao.getByTitle(hotelName, id)
    }

    fun getToursBooked(id: String){
        bookedTours.value = tourDao.getToursBooked(id)
    }

    fun getTitleTourBooked(id: String, hotelName: String){
        bookedTours.value = tourDao.getByTitle(hotelName, id)
    }

    fun deleteTour(tour: BookedTour) {
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