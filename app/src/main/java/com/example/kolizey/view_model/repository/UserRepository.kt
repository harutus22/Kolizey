package com.example.kolizey.view_model.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.kolizey.db.UserDB
import com.example.kolizey.db.dao.UserDao
import com.example.kolizey.model.User
import kotlinx.coroutines.*

class UserRepository(private val app: Application) {
    companion object{
        private lateinit var userDao: UserDao
    }
    lateinit var user: LiveData<User>

    fun init(id: String) {
        val db = UserDB.getDb(app)
        userDao = db.userDao()
//        user = userDao.loadUser(id)
    }

    fun insert(user: User) = userDao.addUser(user)

//    fun getUserLive(id: String): LiveData<User> = userDao.loadUser(id)

    fun getUser(id: String)= userDao.loadUser(id)


    fun updateUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao.updateUser(user)
        }
    }

    fun checkUser(number: String) = userDao.checkUser(number)

}