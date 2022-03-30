package com.example.kolizey.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Tour(
    @PrimaryKey(autoGenerate = true) val tourId: Int = 0,
    @SerializedName("page") val page: Int,
    @SerializedName("items_per_page") val itemsPerPage: Int,
    @SerializedName("offer_count") val offerCount: Int,
    @SerializedName("hotels") val hotels: ArrayList<Hotel>,
    @SerializedName("hotel_list") val hotelList: ArrayList<Int>,
    @SerializedName("max_page") val maxPage: Int,
    @SerializedName("has_more_pages") val hasMorePages: Boolean,
    @SerializedName("update_key") val updateKey: Int,
    @Expose val userId: String = ""
)
