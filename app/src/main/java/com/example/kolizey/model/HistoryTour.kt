package com.example.kolizey.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class HistoryTour(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val title: String,
    val image: String,
    val date: String,
    val from: String,
    val transportType: Int,
    val to: String,
    val travelers: String,
    val price: String,
    val userId: String = ""): Parcelable
