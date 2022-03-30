package com.example.kolizey.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

@Parcelize
data class Country(val name: String, val flag: Int, val cities: ArrayList<String>): Parcelable
