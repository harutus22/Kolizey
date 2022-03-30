package com.example.kolizey.fragment.bottom_navigation_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kolizey.R
import android.content.ClipData
import android.content.Context

class HelpFragment : Fragment() {

    private lateinit var number: TextView
    private lateinit var email: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        number = view.findViewById(R.id.number)
        email = view.findViewById(R.id.email)
    }

    override fun onStart() {
        super.onStart()
        number.setOnLongClickListener {
            copyText(number.text.toString())
            return@setOnLongClickListener true
        }
        email.setOnLongClickListener {
            copyText(email.text.toString())
            return@setOnLongClickListener true
        }
    }

    private fun copyText(text: String) {
        val clipboard =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)
    }
}