package com.example.kolizey.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChildAgeModel(var age: String, var isSelected: Boolean = false): Parcelable
