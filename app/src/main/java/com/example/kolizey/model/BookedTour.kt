package com.example.kolizey.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class BookedTour(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var images: ArrayList<String>,
    var discount: String,
    var fav: Int,
    var hotelName: String,
    var rating: Float,
    var ratingCount: String,
    var personCount: String,
    var price: String,
    var date: String,
    var transportType: String,
    var userId: String = ""
) : Parcelable