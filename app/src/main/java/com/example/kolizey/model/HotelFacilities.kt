package com.example.kolizey.model

import com.google.gson.annotations.SerializedName

data class HotelFacilities(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("category_id") val categoryId: String,
    @SerializedName("category") val category: String,
    @SerializedName("is_main") val isMain: Int,
    @SerializedName("is_paid") val isPaid: Int
)
