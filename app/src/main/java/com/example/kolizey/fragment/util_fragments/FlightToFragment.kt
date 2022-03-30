package com.example.kolizey.fragment.util_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kolizey.R
import com.example.kolizey.adapters.CityAdapter
import com.example.kolizey.utils.*

class FlightToFragment : Fragment(), OnCityClick {

    private lateinit var back: ImageView
    private lateinit var recycler: RecyclerView
    private lateinit var accept: AppCompatButton
    private lateinit var cityAdapter: CityAdapter
    private lateinit var cities: ArrayList<String>
    private var cityTo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cities = initList()
        cityAdapter = CityAdapter(cities, this)
    }

    private fun initList() =  arrayListOf<String>("Київ", "Львів", "Одеса", "Харків", "Дніпро", "Запоріжжя" )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flight_to, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back = view.findViewById(R.id.back)
        recycler = view.findViewById(R.id.recycler)
        accept = view.findViewById(R.id.apply)
    }

    override fun onStart() {
        super.onStart()
        back.setOnClickListener { findNavController().popBackStack() }
        recycler.adapter = cityAdapter
        accept.setOnClickListener {
            setFragmentResult(CITY_TO_REQUEST, bundleOf(CITY_TO_BUNDLE to cityTo))
            findNavController().popBackStack()
        }
    }

    override fun cityClick(city: String) {
        cityTo = city
    }

}