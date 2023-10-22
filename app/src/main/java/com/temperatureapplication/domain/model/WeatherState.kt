package com.temperatureapplication.domain.model

import com.temperatureapplication.data.remote.dto.TemperatureData
class WeatherState(
    var temperatureData: TemperatureData? = null,
    val loading: Boolean = false,
    val error: String = ""
)