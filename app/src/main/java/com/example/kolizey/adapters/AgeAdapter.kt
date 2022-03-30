package com.example.kolizey.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kolizey.R
import com.example.kolizey.model.ChildAgeModel

class AgeAdapter(): RecyclerView.Adapter<AgeAdapter.AgeViewHolder>() {
    private var count = 1
    private val childAges: ArrayList<ChildAgeModel> = initAges()
    private var childAgeCount: ArrayList<ChildAgeModel> = ArrayList()

    inner class AgeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val age: TextView = itemView.findViewById(R.id.age)
        private val frameAge: FrameLayout = itemView.findViewById(R.id.frame_age)

        fun bind(childAgeText: ChildAgeModel){
            age.text = childAgeText.age
            frameAge.setOnClickListener {
                changeSelection(childAgeText)
            }
            if (!childAgeText.isSelected) {
                frameAge.background = AppCompatResources.getDrawable(
                    itemView.context,
                    R.drawable.age_not_selected
                )
                age.setTextColor(ContextCompat.getColor(itemView.context, R.color.main_color))
            } else {
                frameAge.background =
                    AppCompatResources.getDrawable(itemView.context, R.drawable.age_selected)
                age.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            }
        }

        private fun textClick(childAgeModel: ChildAgeModel){
            if (childAgeModel.isSelected) {
                frameAge.background = AppCompatResources.getDrawable(
                    itemView.context,
                    R.drawable.age_not_selected
                )
                age.setTextColor(ContextCompat.getColor(itemView.context, R.color.main_color))
                childAgeCount.remove(childAgeModel)
            } else {
                frameAge.background =
                    AppCompatResources.getDrawable(itemView.context, R.drawable.age_selected)
                age.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                childAgeCount.add(childAgeModel)
            }
            childAgeModel.isSelected = !childAgeModel.isSelected
        }

        private fun changeSelection(childAgeModel: ChildAgeModel){
            if (count > childAgeCount.size)
                textClick(childAgeModel)
            else if (count >= childAgeCount.size && childAgeModel.isSelected)
                textClick(childAgeModel)
             else {
                val a = childAgeCount[0]
                for (i in childAges){
                    if (a == i)
                        i.isSelected = false
                }
                childAgeCount.remove(a)
                childAgeModel.isSelected = true
                childAgeCount.add(childAgeModel)
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_age_item, parent, false)
        return AgeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgeViewHolder, position: Int) {
       holder.bind(childAges[position])
    }

    override fun getItemCount() = childAges.size

    private fun initAges() = ArrayList<ChildAgeModel>().apply {
        add(ChildAgeModel("До року", false))
        add(ChildAgeModel("1", false))
        add(ChildAgeModel("2", false))
        add(ChildAgeModel("3", false))
        add(ChildAgeModel("4", false))
        add(ChildAgeModel("5", false))
        add(ChildAgeModel("6", false))
        add(ChildAgeModel("7", false))
        add(ChildAgeModel("8", false))
        add(ChildAgeModel("9", false))
        add(ChildAgeModel("10", false))
        add(ChildAgeModel("11", false))
        add(ChildAgeModel("12", false))
        add(ChildAgeModel("13", false))
        add(ChildAgeModel("14", false))
        add(ChildAgeModel("15", false))
        add(ChildAgeModel("16", false))
    }

    fun changeChildCount(new: Int){
        if (new in 1 until count) {
            val a = childAgeCount[childAgeCount.size - 1]
            a.isSelected = !a.isSelected
            childAgeCount.remove(a)
        }
        count = if (new < 1)
            1
        else
            new
    }

    fun getAges(): List<String>{
        val intArray = ArrayList<String>()
        for (i in childAgeCount)
            intArray.add(i.age)
        return intArray.toList()
    }
}