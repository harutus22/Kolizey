package com.example.kolizey.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kolizey.R
import com.example.kolizey.model.BookedTour
import com.example.kolizey.utils.OnProfileBookedTourClicked
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class ProfileBookedAdapter(
    private val list: ArrayList<BookedTour>,
    private val onProfileBookedTourClicked: OnProfileBookedTourClicked
) : RecyclerView.Adapter<ProfileBookedAdapter.ProfileBookViewHolder>() {

    inner class ProfileBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var images: SliderView = itemView.findViewById(R.id.slider)
        private var discount: TextView = itemView.findViewById(R.id.discount)
        private var hotel: TextView = itemView.findViewById(R.id.hotel_name)
        private var fav: ImageView = itemView.findViewById(R.id.discount)
        private var ratingBar: SimpleRatingBar = itemView.findViewById(R.id.rating_bar)
        private var ratingCount: TextView = itemView.findViewById(R.id.rating_count)
        private var personCount: TextView = itemView.findViewById(R.id.person_count)
        private var price: TextView = itemView.findViewById(R.id.price)
        private var date: TextView = itemView.findViewById(R.id.date)
        private var transportType: TextView = itemView.findViewById(R.id.transport_type)

        fun bind(bookedTour: BookedTour) {
            itemView.setOnClickListener {
                onProfileBookedTourClicked.onBookedClicked(bookedTour)
            }
            transportType.text = bookedTour.transportType
            date.text = bookedTour.date
            price.text = bookedTour.price
            personCount.text = bookedTour.personCount
            ratingCount.text = bookedTour.ratingCount
            ratingBar.rating = bookedTour.rating
            fav.setImageResource(bookedTour.fav)
            hotel.text = bookedTour.hotelName
            discount.text = bookedTour.discount
            images.setSliderAdapter(BookedSliderAdapter(bookedTour.images))
            images.setIndicatorAnimation(IndicatorAnimationType.WORM)
            images.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileBookedAdapter.ProfileBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_tours_item, parent, false)
        return ProfileBookViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProfileBookedAdapter.ProfileBookViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun updateList(newList: ArrayList<BookedTour>){
        list.addAll(newList)
        notifyDataSetChanged()
    }
}