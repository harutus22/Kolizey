package com.example.kolizey.model

import com.google.gson.annotations.SerializedName

data class Offer(
    @SerializedName("key") val key: String,
    @SerializedName("meal_type") val mealType: String,
    @SerializedName("meal_type_full") val mealTypeFull: String,
    @SerializedName("room_type") val roomType: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("date_from") val dateFrom: String,
    @SerializedName("is_combined") val isCombined: Int,
    @SerializedName("accomodation_kn") val accomodationKn: String,
    @SerializedName("currency_id") val currencyId: Int,
    @SerializedName("prices") val prices: ArrayList<Price>,
    @SerializedName("is_promo") val isPromo: Int,
    @SerializedName("is_dynamic_packet") val isDynamicPacket: Int,
    @SerializedName("is_instant_confirmation") val isInstantConfirmation: Int,
    @SerializedName("from_city") val fromCity: String,
    @SerializedName("transport_type") val transportType: String,
    @SerializedName("transfer") val transfer: Int,
    @SerializedName("showcases") val showcases: String
)
