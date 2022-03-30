package com.example.kolizey.fragment.util_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kolizey.R
import com.example.kolizey.adapters.AgeAdapter
import com.example.kolizey.model.Children
import com.example.kolizey.model.Passengers
import com.example.kolizey.utils.CITY_TO_BUNDLE
import com.example.kolizey.utils.CITY_TO_REQUEST
import com.example.kolizey.utils.PASSENGER_BUNDLE
import com.example.kolizey.utils.PASSENGER_REQUEST
import androidx.recyclerview.widget.GridLayoutManager




class PassengerFragment : Fragment() {

    private lateinit var minusAdult: ImageView
    private lateinit var plusAdult: ImageView
    private lateinit var plusChild: ImageView
    private lateinit var minusChild: ImageView
    private lateinit var back: ImageView
    private lateinit var child: TextView
    private lateinit var adult: TextView
    private lateinit var recyclerChild: RecyclerView
    private lateinit var recyclerLayout: ConstraintLayout
    private lateinit var accept: AppCompatButton
    private var childCount = 0
    private var adultCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        minusAdult = view.findViewById(R.id.decrease_adult)
        plusAdult = view.findViewById(R.id.increase_adult)
        plusChild = view.findViewById(R.id.increase_child)
        minusChild = view.findViewById(R.id.decrease_child)
        child = view.findViewById(R.id.children_count)
        adult = view.findViewById(R.id.adult_count)
        recyclerChild = view.findViewById(R.id.age_recycler)
        back = view.findViewById(R.id.back)
        recyclerLayout = view.findViewById(R.id.child_age)
        accept = view.findViewById(R.id.apply)
    }

    override fun onStart() {
        super.onStart()
        val adapter = AgeAdapter()
        val layoutManager = GridLayoutManager(requireContext(), 12, GridLayoutManager.VERTICAL, false)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val ret = when(position){
                    0 -> 4
                    else ->2
                }
                return ret
            }

        }
        recyclerChild.layoutManager = layoutManager
        recyclerChild.adapter = adapter
        minusChild.setOnClickListener {
            childCount--
            if (childCount < 0)
                childCount = 0
            else {
                if (childCount == 0)
                    recyclerLayout.visibility = View.GONE
            }
            child.text = childCount.toString()
            adapter.changeChildCount(childCount)
        }

        plusChild.setOnClickListener {
            childCount++
            if (childCount > 0)
                recyclerLayout.visibility = View.VISIBLE
            child.text = childCount.toString()
            adapter.changeChildCount(childCount)
        }

        minusAdult.setOnClickListener {
            --adultCount
            if (adultCount < 0)
                adultCount = 0
            adult.text = adultCount.toString()
        }
        plusAdult.setOnClickListener {
            ++adultCount
            adult.text = adultCount.toString()
        }
        back.setOnClickListener {
            findNavController().popBackStack()
        }
        accept.setOnClickListener {
            setFragmentResult(PASSENGER_REQUEST, bundleOf(PASSENGER_BUNDLE to Passengers(adultCount, Children(childCount, adapter.getAges()))))
            findNavController().popBackStack()
        }
    }
}