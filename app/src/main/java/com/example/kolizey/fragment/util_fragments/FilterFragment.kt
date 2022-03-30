package com.example.kolizey.fragment.util_fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.util.Pair
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.kolizey.R
import com.example.kolizey.model.Passengers
import com.example.kolizey.utils.*
import com.example.kolizey.utils.CITY_TO_REQUEST
import com.google.android.material.slider.RangeSlider
import kotlinx.android.synthetic.main.activity_main.*
import worker8.com.github.radiogroupplus.RadioGroupPlus
import com.google.android.material.datepicker.MaterialDatePicker

import com.google.android.material.datepicker.CompositeDateValidator

import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CalendarConstraints.DateValidator

import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import java.util.*
import kotlin.collections.ArrayList


class FilterFragment : Fragment() {

    private lateinit var rangeSlider: RangeSlider
    private lateinit var fromEdit: TextView
    private lateinit var toEdit: TextView
    private lateinit var radioGroup: RadioGroupPlus
    private lateinit var close: ImageView
    private lateinit var flightFrom: TextView
    private lateinit var flightTo: TextView
    private lateinit var passengers: TextView
    private lateinit var date: TextView
    private lateinit var accept: AppCompatButton
    private var cityToGo = ""
    private var cityFrom = ""
    private var passenger: Passengers? = null
    private var dateRange = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().bottom_navigation.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rangeSlider = view.findViewById(R.id.slider)
        fromEdit = view.findViewById(R.id.from_edit)
        toEdit = view.findViewById(R.id.to_edit)
        radioGroup = view.findViewById(R.id.radio_group_plus)
        close = view.findViewById(R.id.close)
        flightFrom = view.findViewById(R.id.flight_from)
        flightTo = view.findViewById(R.id.visit)
        accept = view.findViewById(R.id.apply)
        passengers = view.findViewById(R.id.who_goes)
        date = view.findViewById(R.id.date)
    }

    override fun onStart() {
        super.onStart()
        rangeSlider.addOnChangeListener{ slider, value, from ->
            fromEdit.text = slider.values[0].toString() + "₴"
            toEdit.text = slider.values[1].toString() + "₴"
        }
        close.setOnClickListener {
            goBack()
        }
        flightFrom.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_flightToFragment)
        }
        flightTo.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_flightFromFragment)
        }
        setFragmentResultListener(CITY_REQUEST) { requestKey, bundle ->
            cityToGo = bundle.getString(CITY_BUNDLE)!!
        }
        setFragmentResultListener(CITY_TO_REQUEST) { requestKey, bundle ->
            cityFrom = bundle.getString(CITY_TO_BUNDLE)!!
        }
        setFragmentResultListener(PASSENGER_REQUEST) { requestKey, bundle ->
            passenger = bundle.getParcelable(PASSENGER_BUNDLE)!!
        }
        passengers.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_passengerFragment)
        }
        date.setOnClickListener {
            openDate()
        }
        accept.setOnClickListener {
            
        }
    }

    private fun goBack(){
        requireActivity().bottom_navigation.visibility = View.VISIBLE
        findNavController().popBackStack()
    }

    private fun openDate(){
        val builderRange: MaterialDatePicker.Builder<Pair<Long, Long>> =
            MaterialDatePicker.Builder.dateRangePicker()
        val constraintsBuilderRange = CalendarConstraints.Builder()

        val dateValidatorMin: DateValidator = DateValidatorPointForward.from(System.currentTimeMillis())

        val listValidators = ArrayList<DateValidator>()
        listValidators.add(dateValidatorMin)
        val validators = CompositeDateValidator.allOf(listValidators)
        constraintsBuilderRange.setValidator(validators)

        val pickerRange = builderRange.build()
        pickerRange.show(requireActivity().supportFragmentManager, pickerRange.toString())
        pickerRange.addOnPositiveButtonClickListener {
            val from = DateFormat.format(getString(R.string.date_format), Date(it.first)).toString()
            val to = DateFormat.format(getString(R.string.date_format), Date(it.second)).toString()
            dateRange = "$from - $to"
        }
    }
}