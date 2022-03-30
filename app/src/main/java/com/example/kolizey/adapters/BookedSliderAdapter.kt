package com.example.kolizey.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kolizey.R
import com.smarteist.autoimageslider.SliderViewAdapter

class BookedSliderAdapter(private val images: ArrayList<String>): SliderViewAdapter<BookedSliderAdapter.BookedSliderViewHolder>() {

    inner class BookedSliderViewHolder(itemView: View): SliderViewAdapter.ViewHolder(itemView){
        private val image = itemView.findViewById<ImageView>(R.id.image)

        fun bind(url: String){
            Glide.with(itemView)
                .load(url)
                .fitCenter()
                .into(image);
        }
    }

    override fun getCount() = images.size

    override fun onCreateViewHolder(parent: ViewGroup?): BookedSliderViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.slider_item_view, parent, false)
        return BookedSliderViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: BookedSliderViewHolder?, position: Int) {
        viewHolder?.bind(images[position])
    }

}