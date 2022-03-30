package com.example.kolizey.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var surname: String,
    var telephone: String,
    var email: String,
    var city: String = "",
    var image: String = ""
)
