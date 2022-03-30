package com.example.kolizey.fragment.util_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kolizey.R
import com.example.kolizey.model.User
import com.example.kolizey.utils.USER_ID
import com.example.kolizey.utils.setInt
import com.example.kolizey.view_model.EntireViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {

    private lateinit var back: ImageButton
    private lateinit var numberTxt: TextView
    private lateinit var numberEdit: TextInputEditText
    private lateinit var codeAcceptEdit: TextInputEditText
    private lateinit var signIn: AppCompatButton
    private lateinit var userViewModel: EntireViewModel
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this)[EntireViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberTxt = view.findViewById(R.id.number_txt)
        numberEdit = view.findViewById(R.id.number_edit)
        codeAcceptEdit = view.findViewById(R.id.code_edit)
        signIn = view.findViewById(R.id.sign_in)
        back = view.findViewById(R.id.back)
    }

    override fun onStart() {
        super.onStart()
        numberEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                numberTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_selected))
            else
                numberTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_unselected))
        }

        back.setOnClickListener {
            findNavController().popBackStack()
        }

        signIn.setOnClickListener {
            val number = numberEdit.text!!.isEmpty()
            val code = codeAcceptEdit.text!!.isEmpty()

            checkEdit(number, numberEdit)
            checkEdit(code, codeAcceptEdit)
            val numberCheck = numberEdit.text.toString()
            if (!number && !code){
                CoroutineScope(Dispatchers.Main).launch{
                    user = userViewModel.checkUser(numberCheck)
                    if (user?.telephone != numberEdit.text.toString()){
                        Toast.makeText(requireContext(), "Number don't match", Toast.LENGTH_SHORT).show()
                    } else {
                        setInt(USER_ID, user!!.id, requireContext())
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun checkEdit(name: Boolean, textInputEditText: TextInputEditText){
        if (name){
            textInputEditText.error = "This field must be filled!!!"
        } else {
            textInputEditText.error = null
        }
    }
}