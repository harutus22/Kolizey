package com.example.kolizey.utils

import androidx.room.TypeConverter
import com.example.kolizey.model.*
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converters {
    @TypeConverter
    fun fromStringIntoHotel(value: String?): ArrayList<Hotel?>? {
        val listType: Type = object : TypeToken<ArrayList<Hotel?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListIntoHotel(list: ArrayList<Hotel?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringIntoBooked(value: String?): ArrayList<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListIntoBooked(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }



    @TypeConverter
    fun fromStringIntoInt(value: String?): ArrayList<Int?>? {
        val listType: Type = object : TypeToken<ArrayList<Int?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListIntoInt(list: ArrayList<Int?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringIntoHotelFacilities(value: String?): ArrayList<HotelFacilities?>? {
        val listType: Type = object : TypeToken<ArrayList<HotelFacilities?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListIntoHotelFacilities(list: ArrayList<HotelFacilities?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringIntoImage(value: String?): ArrayList<Image?>? {
        val listType: Type = object : TypeToken<ArrayList<Image?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListIntoImage(list: ArrayList<Image?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringIntoOffer(value: String?): ArrayList<Offer?>? {
        val listType: Type = object : TypeToken<ArrayList<Offer?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListIntoOffer(list: ArrayList<Offer?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringIntoPrice(value: String?): ArrayList<Price?>? {
        val listType: Type = object : TypeToken<ArrayList<Price?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListIntoPrice(list: ArrayList<Price?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}