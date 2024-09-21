package com.temperatureapplication.presentation.current_temperature

import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.temperatureapplication.R
import com.temperatureapplication.data.remote.dto.TemperatureData

class WeatherForecastRecyclerAdapter(val data: List<TemperatureData>) :
    RecyclerView.Adapter<WeatherForecastRecyclerAdapter.WeatherForecastViewHolder>() {

    private var list = mutableListOf<TemperatureData>()

    class WeatherForecastViewHolder(view: View) : ViewHolder(view) {
        // Define click listener for the ViewHolder's View
        val tvTemperature: TextView = view.findViewById(R.id.tv_temperature)
        val tvDay: TextView = view.findViewById(R.id.tv_day)
    }

    fun setContentList(list: ArrayList<TemperatureData>) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun getDate(milliSeconds: Long): String? {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return getWeekDay(calendar.get(Calendar.DAY_OF_WEEK))
    }

    private fun getWeekDay(dayOfWeek: Int): String? {
        return when (dayOfWeek) {
            1 -> "Sunday"
            2 -> "Monday"
            3 -> "Tuesday"
            4 -> "Wednesday"
            5 -> "Thursday"
            6 -> "Friday"
            7 -> "Saturday"
            else -> ""
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false)
        return WeatherForecastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder.tvTemperature.text = "${this.list[position].main?.temp}°C"
        holder.tvDay.text = getDate(this.list[position].dt!!)
    }


}