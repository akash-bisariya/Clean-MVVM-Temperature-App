package com.temperatureapplication.presentation.current_temperature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.temperatureapplication.R
import com.temperatureapplication.presentation.current_temperature.viewmodel.WeatherViewModel
import com.temperatureapplication.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_forecast)
        val pbProgress = findViewById<ProgressBar>(R.id.pb_progress_bar)
        val tvTemperature = findViewById<TextView>(R.id.tv_temperature)
        val tvCity = findViewById<TextView>(R.id.tv_city)


        lifecycle.coroutineScope.launch {
            weatherViewModel.getWeatherData(Constants.APP_ID,Constants.CITY,Constants.METRIC)
        }



        lifecycle.coroutineScope.launch(Dispatchers.Main) {
            weatherViewModel.weatherState.collect { weatherState ->
                if (weatherState.loading) {
                    pbProgress.visibility = View.VISIBLE
                }

                if (weatherState.error.isNotBlank()) {
                    pbProgress.visibility = View.GONE
                }

                weatherState.temperatureData?.let { it ->
                    pbProgress.visibility = View.GONE
                    it.main?.let{
                        tvTemperature.text = it.temp.toString()
                        tvCity.text = Constants.CITY
                        tvTemperature.visibility = View.VISIBLE
                        tvCity.visibility = View.VISIBLE
                    }


//                    list.add(it)
//
//                    adapter.setContentList(list)
//                    recyclerView.adapter = adapter
                }
            }
        }

    }
}