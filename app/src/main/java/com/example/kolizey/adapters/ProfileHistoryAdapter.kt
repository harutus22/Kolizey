package com.example.kolizey.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kolizey.R
import com.example.kolizey.model.HistoryTour
import com.example.kolizey.utils.OnProfileHistoryTourClicked
import com.google.android.material.imageview.ShapeableImageView

class ProfileHistoryAdapter(
    private var list: ArrayList<HistoryTour>,
    private var onProfileHistoryTourClicked: OnProfileHistoryTourClicked
) :
    RecyclerView.Adapter<ProfileHistoryAdapter.ProfileHistoryViewHolder>() {

    inner class ProfileHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tourImage: ShapeableImageView = itemView.findViewById(R.id.tour_image)
        private val tourTitle: TextView = itemView.findViewById(R.id.tour_title)
        private val tourDate: TextView = itemView.findViewById(R.id.date)
        private val tourFrom: TextView = itemView.findViewById(R.id.from)
        private val tourType: ImageView = itemView.findViewById(R.id.transport_type)
        private val tourTo: TextView = itemView.findViewById(R.id.to)
        private val tourTravelers: TextView = itemView.findViewById(R.id.travelers)
        private val tourPrice: TextView = itemView.findViewById(R.id.price)

        fun bind(historyTour: HistoryTour) {
            itemView.setOnClickListener {
                onProfileHistoryTourClicked.onHistoryClicked(historyTour)
            }
            Glide.with(itemView.context).load(historyTour.image).into(tourImage);
            tourTitle.text = historyTour.title
            tourDate.text = historyTour.date
            tourFrom.text = historyTour.from
            tourType.setImageResource(historyTour.transportType)
            tourTo.text = historyTour.to
            tourTravelers.text = historyTour.travelers
            tourPrice.text = historyTour.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileHistoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.tour_history_item, parent, false)
        return ProfileHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileHistoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun updateList(newList: ArrayList<HistoryTour>){
        list.addAll(newList)
        notifyDataSetChanged()
    }
}