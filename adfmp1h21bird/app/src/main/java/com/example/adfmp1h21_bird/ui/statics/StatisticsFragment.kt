package com.example.adfmp1h21_bird.ui.statics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.adfmp1h21_bird.R
import com.example.adfmp1h21_bird.database.NoteDatabase
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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

        val startDate:TextView = rootView.findViewById(R.id.stat_start_date_textView)
        val numberFoto:TextView = rootView.findViewById(R.id.stat_number_foto_textView)
        val birdsInTags:TextView = rootView.findViewById(R.id.stat_birds_in_tags_textView)
        val redBirdCount:TextView = rootView.findViewById(R.id.stat_red_bird_count_textView)

        val stat = getData()
        startDate.text = stat.date
        numberFoto.text = stat.number_foto.toString()
        birdsInTags.text = stat.birds_in_tags.toString()
        redBirdCount.text = stat.red_birds_count.toString()


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

        val notes = NoteDatabase.getInstance(requireContext()).getAllNotes()

        var oldestNoteIndex = -1
        var oldestTime: Long = 0

        for ((i, note) in notes.withIndex()) {
            try {
                val l = LocalDate.parse(note.date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                val noteTime = l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond

                if (noteTime > oldestTime) {
                    oldestTime = noteTime
                    oldestNoteIndex = i
                }
            }
            catch(e: Exception) {}
        }

        var date = "31.02.2014"

        if (oldestNoteIndex != -1) {
            date = notes[oldestNoteIndex].date
        }

        val temp = Statistics(
            date,
            notes.size,
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