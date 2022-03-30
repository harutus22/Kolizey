package com.example.kolizey.fragment.util_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.style.UnderlineSpan
import android.text.SpannableString
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.kolizey.R
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import com.smarteist.autoimageslider.SliderView

class DetailedTourFragment : Fragment() {

    private lateinit var dateText: TextView
    private lateinit var slider: SliderView
    private lateinit var back: ImageView
    private lateinit var fav: ImageView
    private lateinit var hotelName: TextView
    private lateinit var rating: SimpleRatingBar
    private lateinit var hotelRating: SimpleRatingBar
    private lateinit var ratingCount: TextView
    private lateinit var flightFrom: TextView
    private lateinit var passengersCount: TextView
    private lateinit var hotelInfo: TextView
    private lateinit var hotelType: TextView
    private lateinit var gridLayout: GridLayout
    private lateinit var price: TextView
    private lateinit var book: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_tour, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateText = view.findViewById(R.id.date)
        slider = view.findViewById(R.id.slider)
        back = view.findViewById(R.id.back)
        fav = view.findViewById(R.id.fav)
        hotelName = view.findViewById(R.id.hotel_name)
        rating = view.findViewById(R.id.rating_bar)
        hotelRating = view.findViewById(R.id.rating_hotel)
        ratingCount = view.findViewById(R.id.rating_count)
        flightFrom = view.findViewById(R.id.flight_from)
        passengersCount = view.findViewById(R.id.person_count)
        hotelInfo = view.findViewById(R.id.about_hotel)
        hotelType = view.findViewById(R.id.hotel_type)
        gridLayout = view.findViewById(R.id.amenities_grid)
        price = view.findViewById(R.id.price)
        book = view.findViewById(R.id.apply)
    }

    override fun onStart() {
        super.onStart()
    }

    private fun underLineDate(date: String){
        val content = SpannableString(date)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        dateText.text = content
    }
}