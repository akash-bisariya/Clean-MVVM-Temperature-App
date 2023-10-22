package com.temperatureapplication.presentation.current_temperature

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.temperatureapplication.R
import com.temperatureapplication.data.remote.dto.ForecastData
import com.temperatureapplication.data.remote.dto.TemperatureData
import com.temperatureapplication.presentation.current_temperature.viewmodel.WeatherViewModel
import com.temperatureapplication.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val list: ArrayList<TemperatureData> = ArrayList()
    private val adapter = WeatherForecastRecyclerAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvForeCastData = findViewById<RecyclerView>(R.id.rv_forecast)
        val pbProgress = findViewById<ProgressBar>(R.id.pb_progress_bar)
        val tvTemperature = findViewById<TextView>(R.id.tv_temperature)
        val tvCity = findViewById<TextView>(R.id.tv_city)


        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rvForeCastData.layoutManager = layoutManager


        lifecycle.coroutineScope.launch {
            weatherViewModel.getWeatherData(Constants.APP_ID, Constants.CITY, Constants.METRIC)
            weatherViewModel.getWeatherForeCastData(
                Constants.APP_ID,
                Constants.CITY,
                Constants.METRIC
            )
        }



        lifecycle.coroutineScope.launch(Dispatchers.Main) {
            weatherViewModel.weatherState.collect { weatherState ->
                if (weatherState.loading) {
                    pbProgress.visibility = View.VISIBLE
                }

                if (weatherState.error.isNotBlank()) {
                    pbProgress.visibility = View.GONE
                    Toast.makeText(this@MainActivity,
                        "Error fetching Weather Data: ${weatherState.error}",Toast.LENGTH_SHORT).show()
                }

                weatherState.temperatureData?.let { it ->
                    it.main?.let {
                        tvTemperature.text = "${it.temp}°C"
                        tvCity.text = Constants.CITY
                        tvTemperature.visibility = View.VISIBLE
                        tvCity.visibility = View.VISIBLE
                    }

                }
            }
        }


        lifecycle.coroutineScope.launch(Dispatchers.Main) {
            weatherViewModel.weatherForecastState.collect { weatherForecastState ->
                if (weatherForecastState.loading) {
                    pbProgress.visibility = View.VISIBLE
                }

                if (weatherForecastState.error.isNotBlank()) {
                    pbProgress.visibility = View.GONE
                    Toast.makeText(this@MainActivity,
                        "Error fetching Forecast Data: ${weatherForecastState.error}",Toast.LENGTH_SHORT).show()
                }

                weatherForecastState.forecastData?.list?.let { it ->
                    pbProgress.visibility = View.GONE
                    rvForeCastData.visibility = View.VISIBLE
                    list.addAll(it)
                    adapter.setContentList(list)
                    rvForeCastData.adapter = adapter
                }
            }
        }

    }


}