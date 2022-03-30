package com.example.kolizey.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.kolizey.R
import com.example.kolizey.model.Country
import com.example.kolizey.utils.OnCountryClick

class CountryFromAdapter(countries: ArrayList<Country>, private val countryClick: OnCountryClick): RecyclerView.Adapter<CountryFromAdapter.CountryViewHolder>() {
    private var previous = -1
    private var preRadioBtn: AppCompatRadioButton? = null
    private val countries: ArrayList<Country> = ArrayList()

    init {
        this.countries.addAll(countries)
    }

    inner class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val checked: AppCompatRadioButton = itemView.findViewById(R.id.is_checked)
        private val image: AppCompatImageView = itemView.findViewById(R.id.image)
        private val countryName: TextView = itemView.findViewById(R.id.country_name)
        private val next: AppCompatImageView = itemView.findViewById(R.id.next)

        fun bind(country: Country, position: Int){
            image.setImageResource(country.flag)
            countryName.text = country.name
            checked.setOnClickListener {
                if (previous != -1 && position != previous) {
                    preRadioBtn!!.isChecked = false
                }
                previous = position
                preRadioBtn = checked
            }
            next.setOnClickListener {
                countryClick.countryClick(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_country_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position], position)
    }

    override fun getItemCount() = countries.size

    fun refreshList(newList: ArrayList<Country>){
        countries.clear()
        countries.addAll(newList)
        notifyDataSetChanged()
    }
}