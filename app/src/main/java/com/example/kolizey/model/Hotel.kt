package com.example.kolizey.model

import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("country") val country: String,
    @SerializedName("region_id") val regionId: Int,
    @SerializedName("region") val region: String,
    @SerializedName("hotel_rating") val hotelRating: String,
    @SerializedName("adult_amount") val adultAmount: Int,
    @SerializedName("child_amount") val childAmount: Int,
    @SerializedName("accomodation") val accomodation: String,
    @SerializedName("min_price") val minPrice: Int,
    @SerializedName("hotel_id") val hotelId: Int,
    @SerializedName("hotel") val hotel: String,
    @SerializedName("hotel_review_rate") val hotelReviewRate: String,
    @SerializedName("hotel_review_count") val hotelReviewCount: Int,
    @SerializedName("hotel_facilities") val hotelFacilities: ArrayList<HotelFacilities>,
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String,
    @SerializedName("wifi_free") val wifiFree: Boolean,
    @SerializedName("images") val images: ArrayList<Image>,
    @SerializedName("offers") val offers: ArrayList<Offer>
)
