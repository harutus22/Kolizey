package com.example.kolizey.fragment.util_fragments

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.example.kolizey.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.lang.Exception
import java.util.*

import android.content.Intent
import com.example.kolizey.utils.GALLERY_REQUEST

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.kolizey.model.User
import com.example.kolizey.view_model.EntireViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileFragment : Fragment() {

    private lateinit var back: ImageButton
    private lateinit var profileImage: ShapeableImageView
    private lateinit var editPhoto: ImageView
    private lateinit var nameTxt: TextView
    private lateinit var nameEdit: TextInputEditText
    private lateinit var surnameTxt: TextView
    private lateinit var surnameEdit: TextInputEditText
    private lateinit var numberTxt: TextView
    private lateinit var numberEdit: TextInputEditText
    private lateinit var mailTxt: TextView
    private lateinit var mailEdit: TextInputEditText
    private lateinit var locationTxt: TextView
    private lateinit var locationEdit: TextInputEditText
    private lateinit var save: AppCompatButton
    private var selectedImage: Uri? = null
    private lateinit var userViewModel: EntireViewModel
    private lateinit var user: User
    private var bitmap: Bitmap? = null
    private var isFirst = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this)[EntireViewModel::class.java]
        user = userViewModel.getUser(requireContext())
        requireActivity().bottom_navigation.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
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
        locationTxt = view.findViewById(R.id.location_txt)
        locationEdit = view.findViewById(R.id.location_edit)
        editPhoto = view.findViewById(R.id.edit_photo)
        save = view.findViewById(R.id.save)
        profileImage = view.findViewById(R.id.profile_image)
    }

    override fun onStart() {
        super.onStart()
        nameEdit.setText(user.name)
        surnameEdit.setText(user.surname)
        numberEdit.setText(user.telephone)
        mailEdit.setText(user.email)
        locationEdit.setText(user.city)
        if (user.image != "" && !isFirst)
            CoroutineScope(Dispatchers.Main).launch {
                val files2 = requireContext().filesDir.absoluteFile
                val file = File(files2,  user.image)
                val bytes = file.readBytes()
                profileImage.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
//                val files = requireContext().filesDir.listFiles()
//                files?.filter { it.canRead() && it.isFile && it.name.equals(user.image) }?.map {
//                    val bytes = it.readBytes()
//                    profileImage.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
//                }
            }
        nameEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                nameTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_selected))
            else
                nameTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_unselected))
        }
        surnameEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                surnameTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_selected))
            else
                surnameTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_unselected))
        }
        numberEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                numberTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_selected))
            else
                numberTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_unselected))
        }
        mailEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                mailTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_selected))
            else
                mailTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_unselected))
        }
        locationEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                locationTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_selected))
            else
                locationTxt.setTextColor(ContextCompat.getColor(v.context, R.color.text_unselected))
        }

        editPhoto.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }

        back.setOnClickListener {
            goBack()
        }

        save.setOnClickListener {
            val name = nameEdit.text!!.isEmpty()
            val surname = surnameEdit.text!!.isEmpty()
            val number = numberEdit.text!!.isEmpty()
            val mail = mailEdit.text!!.isEmpty()
            val location = locationEdit.text!!.isEmpty()

            checkEdit(name, nameEdit)
            checkEdit(surname, surnameEdit)
            checkEdit(number, numberEdit)
            checkEdit(mail, mailEdit)
            checkEdit(location, locationEdit)

            if (!name && !surname && !number && !mail && !location) {
//                saveImage(selectedImage.toString())
                if (bitmap != null)
                    savePhotoToInternalStorage(UUID.randomUUID().toString(), bitmap!!)
                user.name = nameEdit.text.toString()
                user.surname = surnameEdit.text.toString()
                user.city = locationEdit.text.toString()
                user.email = mailEdit.text.toString()
                userViewModel.insertUser(user)
                goBack()
            }
        }
    }

    private fun goBack(){
        requireActivity().bottom_navigation.visibility = View.VISIBLE
        findNavController().popBackStack()
    }

    private fun saveImage(uri: String?) {
        try {
            if (uri != null) {
                val bitmap = MediaStore.Images.Media.getBitmap(
                    requireContext().contentResolver,
                    uri.toUri()
                )
                val wrapper = ContextWrapper(requireContext().applicationContext)
                var file = wrapper.getDir("profile_pic", Context.MODE_PRIVATE)
                file = File(file, "${UUID.randomUUID()}.jpg")
                try {
                    val stream: OutputStream = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.flush()
                    stream.close()
                } catch (e: IOException) { // Catch the exception
                    e.printStackTrace()
                }
                user.image = file.absolutePath
            }
        } catch (e: Exception) {
            //handle exception
        }
    }

    private fun savePhotoToInternalStorage(filename: String, bmp: Bitmap): Boolean {
        return try {
            requireContext().openFileOutput("$filename.jpg", MODE_PRIVATE).use { stream ->
                    val oldPhoto = user.image
                    deletePhotoFile(oldPhoto)
                    user.image = "$filename.jpg"
                    if (!bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream)) {
                        throw IOException("Couldn't save bitmap.")
                    }
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    private fun deletePhotoFile(photo: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val files = requireContext().filesDir.listFiles()
            files?.filter { it.canRead() && it.isFile && it.name.equals(photo) }?.map {
                if (it.exists()) {
                    if (it.delete()) {
                        System.out.println("file Deleted :" + photo.toUri().path!!)
                    } else {
                        System.out.println("file not Deleted :" + photo.toUri().path!!)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) when (requestCode) {
            GALLERY_REQUEST -> {
                selectedImage = data?.data!!
                try {
                    bitmap =
                        MediaStore.Images.Media.getBitmap(
                            requireContext().contentResolver,
                            selectedImage
                        )
                    isFirst = true
                    profileImage.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    Log.i("TAG", "Some exception $e")
                }
            }
        }
    }

    private fun checkEdit(name: Boolean, textInputEditText: TextInputEditText) {
        if (name) {
            textInputEditText.error = "This field must be filled!!!"
        } else {
            textInputEditText.error = null
        }
    }

}