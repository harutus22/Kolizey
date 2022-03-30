package com.example.kolizey.utils

import com.example.kolizey.model.BookedTour
import com.example.kolizey.model.Country
import com.example.kolizey.model.HistoryTour

interface OnProfileHistoryTourClicked{
    fun onHistoryClicked(historyTour: HistoryTour)
}

interface OnProfileBookedTourClicked{
    fun onBookedClicked(bookedTour: BookedTour)
}

interface OnCountryClick{
    fun countryClick(country: Country)
}

interface OnCityClick{
    fun cityClick(city: String)
}

interface OnAgeClick{
    fun ageClick(age: String)
}