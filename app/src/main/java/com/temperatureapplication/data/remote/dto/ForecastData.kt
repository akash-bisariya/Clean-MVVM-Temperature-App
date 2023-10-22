package com.temperatureapplication.data.remote.dto

data class ForecastData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<TemperatureData>,
    val message: Int
)