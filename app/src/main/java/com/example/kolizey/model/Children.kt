package com.example.kolizey.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Children(var count: Int, var agesCount: List<String>): Parcelable