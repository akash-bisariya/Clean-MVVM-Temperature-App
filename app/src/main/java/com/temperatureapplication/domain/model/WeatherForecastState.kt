package com.temperatureapplication.domain.model

import com.temperatureapplication.data.remote.dto.ForecastData

class WeatherForecastState(
    var forecastData: ForecastData? = null,
    val loading: Boolean = false,
    val error: String = ""
)