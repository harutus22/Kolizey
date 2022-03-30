package com.example.kolizey.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("1") val one: Int,
    @SerializedName("2") val two: Int,
    @SerializedName("10") val ten: Int,
)
