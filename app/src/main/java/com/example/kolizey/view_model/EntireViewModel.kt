package com.example.kolizey.view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kolizey.model.BookedTour
import com.example.kolizey.model.HistoryTour
import com.example.kolizey.model.Tour
import com.example.kolizey.model.User
import com.example.kolizey.utils.USER_ID
import com.example.kolizey.utils.getSharedInt
import com.example.kolizey.view_model.repository.TourBookedRepository
import com.example.kolizey.view_model.repository.TourHistoryRepository
import com.example.kolizey.view_model.repository.TourRepository
import com.example.kolizey.view_model.repository.UserRepository

class EntireViewModel(application: Application): AndroidViewModel(application) {
    private var repoTour: TourRepository = TourRepository(application)
    private var repoUser: UserRepository = UserRepository(application)
    private var tourHistory: TourHistoryRepository = TourHistoryRepository(application)
    private var tourBooked: TourBookedRepository = TourBookedRepository(application)
    private var tours: LiveData<List<Tour>>

//    var user: LiveData<User>

    init {
        repoTour.init()
        repoUser.init(getSharedInt(USER_ID, application).toString())
        tours = repoTour.getTour()
        tourHistory.init(getSharedInt(USER_ID, application).toString())
        tourBooked.init(getSharedInt(USER_ID, application).toString())
//        user = repoUser.getUserLive(getSharedInt(USER_ID, application).toString())
    }

    fun insertTour(tour: Tour){
        repoTour.insert(tour)
    }

    fun deleteTour(tour: Tour){
        repoTour.deleteTour(tour)
    }

    fun deleteAllTours(){
        repoTour.deleteAllTours()
    }

    fun getUser(context: Context) = repoUser.getUser(getSharedInt(USER_ID, context).toString())

    fun insertUser(user: User) = repoUser.insert(user)

    fun updateUser(user: User){
        repoUser.updateUser(user)
    }

    fun checkUser(number: String) = repoUser.checkUser(number)

    fun insertTourHistory(tour: HistoryTour){
        tourHistory.insert(tour)
    }

    fun insertTourBooked(tour: BookedTour){
        tourBooked.insert(tour)
    }

    fun getTourHistory(context: Context) = tourHistory.getTour(getSharedInt(USER_ID, context).toString())
    fun getTourBooked(context: Context): LiveData<List<BookedTour>> = tourBooked.getTour(getSharedInt(USER_ID, context).toString())

    fun getByHotelName(hotelName: String, context: Context) = tourBooked.getByHotelName(hotelName, getSharedInt(
        USER_ID, context).toString())
}