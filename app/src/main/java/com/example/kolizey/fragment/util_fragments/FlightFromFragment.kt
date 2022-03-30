package com.example.kolizey.fragment.util_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kolizey.R
import com.example.kolizey.adapters.CityAdapter
import com.example.kolizey.adapters.CountryFromAdapter
import com.example.kolizey.model.Country
import com.example.kolizey.utils.CITY_BUNDLE
import com.example.kolizey.utils.CITY_REQUEST
import com.example.kolizey.utils.OnCityClick
import com.example.kolizey.utils.OnCountryClick

class FlightFromFragment : Fragment(), OnCountryClick, OnCityClick {

    private lateinit var back: ImageView
    private lateinit var searchView: SearchView
    private lateinit var recycler: RecyclerView
    private lateinit var countryAdapter: CountryFromAdapter
    private lateinit var cityAdapter: CityAdapter
    private lateinit var accept: Button
    private lateinit var countryList: ArrayList<Country>
    private lateinit var filterCountryList: ArrayList<Country>
    private lateinit var filterCityList: ArrayList<String>
    private lateinit var cities: ArrayList<String>
    private lateinit var filterCities: ArrayList<String>
    private var isCountry = false
    private var cityToGo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryList = initList()
        filterCountryList = ArrayList()
        filterCityList = ArrayList()
        filterCities = ArrayList()
        countryAdapter = CountryFromAdapter(countryList, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flight_from, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back = view.findViewById(R.id.back)
        searchView = view.findViewById(R.id.search)
        recycler = view.findViewById(R.id.search_recycler)
        accept = view.findViewById(R.id.apply)
    }

    override fun onStart() {
        super.onStart()
        recycler.adapter = countryAdapter
        back.setOnClickListener {
            if (!isCountry){
                findNavController().popBackStack()
            } else {
                isCountry = false
                recycler.adapter = countryAdapter
            }
        }
        accept.setOnClickListener {
            setFragmentResult(CITY_REQUEST, bundleOf(CITY_BUNDLE to cityToGo))
            findNavController().popBackStack()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == "") {
                    if (!isCountry) {
                        filterCountryList.clear()
                        countryAdapter.refreshList(countryList)
                    } else {
                        filterCityList.clear()
                        cityAdapter.refreshList(cities)
                    }
                }
                else if (newText != null){
                    if (!isCountry) {
                        filterCountryList.clear()
                        filterCountryList.addAll(countryList.filter {
                            it.name.startsWith(newText, true)
                        })
                        countryAdapter.refreshList(filterCountryList)
                    } else {
                        filterCityList.clear()
                        filterCityList.addAll(cities.filter {
                            it.startsWith(newText, ignoreCase = true)
                        })
                        cityAdapter.refreshList(filterCityList)
                    }
                }
                return true
            }

        })

    }

    override fun countryClick(country: Country) {
        cities = country.cities
        isCountry = true
        cityAdapter = CityAdapter(country.cities, this)
        recycler.adapter = cityAdapter
    }

    override fun cityClick(city: String) {
        cityToGo = city
    }

    private fun initList() = ArrayList<Country>().apply {
        add(Country("Europe", R.drawable.flag_france, arrayListOf(getString(R.string.paris), "Belgium", "Bucharest", "London", "Moscow", "Madrid")))
        add(Country(getString(R.string.france), R.drawable.flag_france, arrayListOf(getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris))))
        add(Country(getString(R.string.france), R.drawable.flag_france, arrayListOf(getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris))))
        add(Country(getString(R.string.france), R.drawable.flag_france, arrayListOf(getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris))))
        add(Country(getString(R.string.france), R.drawable.flag_france, arrayListOf(getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris))))
        add(Country(getString(R.string.france), R.drawable.flag_france, arrayListOf(getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris))))
        add(Country(getString(R.string.france), R.drawable.flag_france, arrayListOf(getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris), getString(R.string.paris))))
    }


}