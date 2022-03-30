package com.example.kolizey.fragment.util_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.kolizey.R
import kotlinx.android.synthetic.main.activity_main.*

class IntroductionFragment : Fragment() {

    private lateinit var page: TextView
    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var next: AppCompatButton
    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_introduction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        page = view.findViewById(R.id.page)
        image = view.findViewById(R.id.image)
        title = view.findViewById(R.id.t_name)
        description = view.findViewById(R.id.desc)
        next = view.findViewById(R.id.next)
    }

    override fun onStart() {
        super.onStart()
        next.setOnClickListener {
            count++
            var imgRes: Int = 0
            var tTit: String = ""
            var desc: String = ""
            page.text = "$count / 4"
            when(count){
                2 -> {
                    imgRes = R.drawable.intro_fav
                    tTit = getString(R.string.favourite)
                    desc = getString(R.string.intro_fav)
                }
                3 -> {
                    imgRes = R.drawable.intro_info
                    tTit = getString(R.string.information)
                    desc = getString(R.string.intro_info)
                }
                4 -> {
                    imgRes = R.drawable.intro_help
                    tTit = getString(R.string.help)
                    desc = getString(R.string.intro_help)
                }
                else ->{
                    requireActivity().apply {
                        fragment.visibility = View.GONE
                        bottom_navigation.visibility = View.VISIBLE
                        supportFragmentManager.beginTransaction().remove(this@IntroductionFragment).commit();
                    }
                }
            }
            image.setImageResource(imgRes)
            title.text = tTit
            description.text = desc
        }
    }
}