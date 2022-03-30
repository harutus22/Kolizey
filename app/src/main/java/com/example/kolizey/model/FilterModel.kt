package com.example.kolizey.model

import com.google.gson.annotations.SerializedName

data class FilterMode(
    @SerializedName("country") val country: String = "",
    @SerializedName("from_city") val cityFrom: String = "",
    @SerializedName("hotel_rating") val hotelRating: Int = -1,
    @SerializedName("adult_amount") val adults: Int = 0,
    @SerializedName("child_amount") val children: Int = 0,
    @SerializedName("child_age") val childrenAge: List<Int>? = null,
    @SerializedName("night_from") val startDate: String = "",
    @SerializedName("night_till") val endDate: String = "",
    @SerializedName("price_from") val priceFrom: Int = 0,
    @SerializedName("price_till") val priceTo: Int = 0,
)