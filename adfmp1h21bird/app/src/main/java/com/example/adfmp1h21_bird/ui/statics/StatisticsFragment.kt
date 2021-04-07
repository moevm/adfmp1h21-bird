package com.example.adfmp1h21_bird.ui.statics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.adfmp1h21_bird.R
import com.example.adfmp1h21_bird.note.myNote

class StatisticsFragment : Fragment() {

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    private lateinit var viewModel: StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView:View = inflater.inflate(R.layout.fragment_statistics, container, false)
        viewModel = ViewModelProvider(this).get(StatisticsViewModel::class.java)
//        val textView: TextView = rootView.findViewById(R.id.statics_test)
//        textView.text = " my statics test!! "

        val start_date:TextView = rootView.findViewById(R.id.stat_start_date_textView)
        val number_foto:TextView = rootView.findViewById(R.id.stat_number_foto_textView)
        val birds_in_tags:TextView = rootView.findViewById(R.id.stat_birds_in_tags_textView)
        val red_bird_count:TextView = rootView.findViewById(R.id.stat_red_bird_count_textView)

        val stat = getData()
        start_date.text = stat.date
        number_foto.text = stat.number_foto.toString()
        birds_in_tags.text = stat.birds_in_tags.toString()
        red_bird_count.text = stat.red_birds_count.toString()


        return rootView
    }

    private  class Statistics(
            val date: String,
            val number_foto: Int,
            val birds_in_tags: Int,
            val red_birds_count: Int
    )

    private fun getData(): Statistics {
        // TODO получение данных для статистики

        val temp = Statistics(
                "31.03.2077",
                100500,
                10,
                3
        )
        return temp
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        // TODO: Use the ViewModel
//    }

}