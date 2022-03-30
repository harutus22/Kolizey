package com.example.kolizey.fragment.bottom_navigation_fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kolizey.R
import com.example.kolizey.adapters.ProfileBookedAdapter
import com.example.kolizey.adapters.ProfileHistoryAdapter
import com.example.kolizey.model.BookedTour
import com.example.kolizey.model.HistoryTour
import com.example.kolizey.model.User
import com.example.kolizey.utils.*
import com.example.kolizey.view_model.EntireViewModel
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class ProfileFragment : Fragment(), OnProfileHistoryTourClicked, OnProfileBookedTourClicked {

    private lateinit var signIn: AppCompatButton
    private lateinit var register: AppCompatButton
    private lateinit var logOut: ImageButton
    private lateinit var profileImage: ShapeableImageView
    private lateinit var name: TextView
    private lateinit var telephone: TextView
    private lateinit var email: TextView
    private lateinit var city: TextView
    private lateinit var edit: ImageView
    private lateinit var toggleButton: MaterialButtonToggleGroup
    private var user: User? = null
    private lateinit var viewModel: EntireViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: ProfileHistoryAdapter
    private lateinit var bookedAdapter: ProfileBookedAdapter
    lateinit var listHistory: ArrayList<HistoryTour>
    lateinit var listBooked: ArrayList<BookedTour>
    private var userId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EntireViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userId = getSharedInt(USER_ID, requireContext())
        user = viewModel.getUser(requireContext())
        val layout: Int = if (user != null)
            R.layout.fragment_profile
        else
            R.layout.fragment_profile_loged_out
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (userId == -1) {
            signIn = view.findViewById(R.id.sign_in)
            register = view.findViewById(R.id.register)
        } else {
            logOut = view.findViewById(R.id.log_out)
            profileImage = view.findViewById(R.id.profile_image)
            name = view.findViewById(R.id.name)
            telephone = view.findViewById(R.id.telephone)
            email = view.findViewById(R.id.email)
            city = view.findViewById(R.id.city)
            edit = view.findViewById(R.id.edit)
            toggleButton = view.findViewById(R.id.toggle_button_group)
            recyclerView = view.findViewById(R.id.recycler)
        }
    }

    override fun onStart() {
        super.onStart()
        historyAdapter = ProfileHistoryAdapter(ArrayList(), this)
        bookedAdapter = ProfileBookedAdapter(ArrayList(), this)
        recyclerView.adapter = historyAdapter
        recyclerView.adapter = bookedAdapter
    }

    override fun onResume() {
        super.onResume()
        if (user != null) {
            initProfile()
            edit.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
            }
            logOut.setOnClickListener {
                setInt(USER_ID, -1, requireContext())
                findNavController().navigate(R.id.action_profileFragment_self)
            }
            toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
                when(checkedId){
                    R.id.tour_history -> {
                        recyclerView.adapter = historyAdapter
                    }
                    R.id.tour_booked -> {
                        recyclerView.adapter = bookedAdapter
                    }
                }
            }
        } else {
            signIn.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_signInFragment)
            }

            register.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_registerFragment)
            }
        }
    }

    private fun initProfile() {
        if (user!!.image != "")
            CoroutineScope(Dispatchers.Main).launch {
                val files2 = requireContext().filesDir.absoluteFile
                val file = File(files2, user!!.image)
                val bytes = file.readBytes()
                profileImage.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
//                val files = requireContext().filesDir.listFiles()
//                files?.filter { it.canRead() && it.isFile && it.name.equals(user!!.image) }?.map {
//                    val bytes = it.readBytes()
//                    profileImage.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
//                }
            }
        name.text = "${user!!.name} ${user!!.surname}"
        telephone.text = user!!.telephone
        email.text = user!!.email
        city.text = user!!.city
        viewModel.getTourHistory(requireContext()).observe(this, Observer {
            listHistory = ArrayList(it)
            historyAdapter.updateList(listHistory)
//            recyclerView.adapter = historyAdapter
        })
        viewModel.getTourBooked(requireContext()).observe(this, Observer {
            listBooked = ArrayList(it)
            bookedAdapter.updateList(listBooked)
//            recyclerView.adapter = bookedAdapter
        })
    }

    override fun onHistoryClicked(historyTour: HistoryTour) {

    }

    override fun onBookedClicked(bookedTour: BookedTour) {

    }
}