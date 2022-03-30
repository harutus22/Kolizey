package com.example.kolizey.fragment.bottom_navigation_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kolizey.R

class MyToursFragment : Fragment() {

    private lateinit var searchLayout: LinearLayoutCompat
    private lateinit var toursRecycler: RecyclerView
    private lateinit var nothingFound: LinearLayout
    private lateinit var search: SearchView
    private lateinit var filter: LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_tours, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchLayout = view.findViewById(R.id.search_layout)
        toursRecycler = view.findViewById(R.id.recycler)
        nothingFound = view.findViewById(R.id.nothing_found)
//        search = searchLayout.findViewById(R.id.search)
        filter = searchLayout.findViewById(R.id.filter_text)
    }

    override fun onStart() {
        super.onStart()
//        findNavController().navigate(R.id.action_myToursFragment_to_introductionFragment)
        filter.setOnClickListener {
            findNavController().navigate(R.id.action_myToursFragment_to_filterFragment)
        }
    }
}