package com.example.adfmp1h21_bird.ui.base

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.adfmp1h21_bird.R

class BaseFragment : Fragment() {

//    companion object {
//        fun newInstance() = BaseFragment()
//    }

    private lateinit var viewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView:View = inflater.inflate(R.layout.fragment_base, container, false)

        val textView: TextView = rootView.findViewById(R.id.base_text)
        textView.text = " my base test!! "

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BaseViewModel::class.java)


//        val textView: TextView = root.findViewById(R.id.text_gallery)
//        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
    }

}