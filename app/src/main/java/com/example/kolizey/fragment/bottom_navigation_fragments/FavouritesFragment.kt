package com.example.kolizey.fragment.bottom_navigation_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.kolizey.R
import com.example.kolizey.adapters.ProfileBookedAdapter
import com.example.kolizey.model.BookedTour
import com.example.kolizey.utils.OnProfileBookedTourClicked
import com.example.kolizey.view_model.EntireViewModel

class FavouritesFragment : Fragment(), OnProfileBookedTourClicked {

    private lateinit var viewModel: EntireViewModel
    private lateinit var search: SearchView
    private lateinit var filter: LinearLayoutCompat
    private lateinit var listBooked: ArrayList<BookedTour>
    private lateinit var bookedAdapter: ProfileBookedAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layout: LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EntireViewModel::class.java]
        bookedAdapter = ProfileBookedAdapter(ArrayList(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout = view.findViewById(R.id.search_layout)
        search = layout.findViewById(R.id.search)
        filter = layout.findViewById(R.id.filter_text)
        recyclerView = view.findViewById(R.id.recycler)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getTourBooked(requireContext()).observe(this, Observer {
            listBooked = ArrayList(it)
            bookedAdapter.updateList(listBooked)
//            recyclerView.adapter = bookedAdapter
        })
        recyclerView.adapter = bookedAdapter

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == "")
                    viewModel.getTourBooked(requireContext())
                else if (newText != null)
                    viewModel.getByHotelName(newText, requireContext())

                return true
            }

        })
    }

    override fun onBookedClicked(bookedTour: BookedTour) {

    }
}