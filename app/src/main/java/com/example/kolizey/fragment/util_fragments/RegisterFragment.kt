package com.example.kolizey.fragment.util_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kolizey.R
import com.example.kolizey.model.User
import com.example.kolizey.utils.USER_ID
import com.example.kolizey.utils.setInt
import com.example.kolizey.view_model.EntireViewModel
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {

    private lateinit var back: ImageButton
    private lateinit var nameTxt: TextView
    private lateinit var nameEdit: TextInputEditText
    private lateinit var surnameTxt: TextView
    private lateinit var surnameEdit: TextInputEditText
    private lateinit var numberTxt: TextView
    private lateinit var numberEdit: TextInputEditText
    private lateinit var mailTxt: TextView
    private lateinit var mailEdit: TextInputEditText
    private lateinit var codeAcceptEdit: TextInputEditText
    private lateinit var register: AppCompatButton
    private lateinit var userViewModel: EntireViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this)[EntireViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back = view.findViewById(R.id.back)
        nameTxt = view.findViewById(R.id.name_txt)
        nameEdit = view.findViewById(R.id.name_edit)
        surnameTxt = view.findViewById(R.id.surname_txt)
        surnameEdit = view.findViewById(R.id.surname_edit)
        numberTxt = view.findViewById(R.id.number_txt)
        numberEdit = view.findViewById(R.id.number_edit)
        mailTxt = view.findViewById(R.id.mail_txt)
        mailEdit = view.findViewById(R.id.mail_edit)
        codeAcceptEdit = view.findViewById(R.id.code_edit)
        register = view.findViewById(R.id.register)
    }

    override fun onStart() {
        super.onStart()
        nameEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                nameTxt.setTextColor(getColor(v.context, R.color.text_selected))
            else
                nameTxt.setTextColor(getColor(v.context, R.color.text_unselected))
        }
        surnameEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                surnameTxt.setTextColor(getColor(v.context, R.color.text_selected))
            else
                surnameTxt.setTextColor(getColor(v.context, R.color.text_unselected))
        }
        numberEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                numberTxt.setTextColor(getColor(v.context, R.color.text_selected))
            else
                numberTxt.setTextColor(getColor(v.context, R.color.text_unselected))
        }
        mailEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                mailTxt.setTextColor(getColor(v.context, R.color.text_selected))
            else
                mailTxt.setTextColor(getColor(v.context, R.color.text_unselected))
        }

        back.setOnClickListener {
            findNavController().popBackStack()
        }
        register.setOnClickListener {
            val name = nameEdit.text!!.isEmpty()
            val surname = surnameEdit.text!!.isEmpty()
            val number = numberEdit.text!!.isEmpty()
            val mail = mailEdit.text!!.isEmpty()
            val code = codeAcceptEdit.text!!.isEmpty()

            checkEdit(name, nameEdit)
            checkEdit(surname, surnameEdit)
            checkEdit(number, numberEdit)
            checkEdit(mail, mailEdit)
            checkEdit(code, codeAcceptEdit)

            if (!name && !surname && !number && !mail && !code){
                val resId = userViewModel.insertUser(User(
                    name = nameEdit.text!!.toString(),
                    surname = surnameEdit.text!!.toString(),
                telephone = numberEdit.text!!.toString(),
                email = mailEdit.text!!.toString()))
//                val id = userViewModel.getUser(requireContext())
//                val resId = id.id
                setInt(USER_ID, resId.toInt(), requireContext())
                findNavController().popBackStack()
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