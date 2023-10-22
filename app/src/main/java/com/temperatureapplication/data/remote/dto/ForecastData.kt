package com.temperatureapplication.data.remote.dto

data class ForecastData(
    val city: City? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    val list: List<TemperatureData>? = null,
    val message: Int? = null
)