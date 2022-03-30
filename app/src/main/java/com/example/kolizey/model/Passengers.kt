package com.example.kolizey.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Passengers(val adults: Int, val children: Children? = null): Parcelable
