package com.example.kolizey.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.kolizey.R
import com.example.kolizey.utils.OnCityClick

class CityAdapter(cities: ArrayList<String>, private val cityClick: OnCityClick): RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private var previous = -1
    private var preRadioBtn: AppCompatRadioButton? = null
    private val cities: ArrayList<String> = ArrayList()

    init {
        this.cities.addAll(cities)
    }

    inner class CityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val checked: AppCompatRadioButton = itemView.findViewById(R.id.is_checked)
        private val cityName: TextView = itemView.findViewById(R.id.city_name)

        fun bind(city: String, position: Int){
            cityName.text = city
            checked.setOnClickListener {
                if (previous != -1 && position != previous) {
                    preRadioBtn!!.isChecked = false
                }
                previous = position
                preRadioBtn = checked
                cityClick.cityClick(city)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_city_item, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position], position)
    }

    override fun getItemCount() = cities.size

    fun refreshList(newList: ArrayList<String>){
        cities.clear()
        cities.addAll(newList)
        notifyDataSetChanged()
    }
}