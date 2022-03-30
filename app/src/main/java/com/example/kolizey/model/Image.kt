package com.example.kolizey.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("full") val full: String,
    @SerializedName("thumb") val thumb: String,
    @SerializedName("is_main") val isMain: Int,
)
