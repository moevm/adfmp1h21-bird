package com.example.adfmp1h21_bird.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adfmp1h21_bird.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment(), OnNoteClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var noteItemList: List<NoteRecyclerViewItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        noteItemList = initializeBirdItemList()

        val recyclerView: RecyclerView = rootView.findViewById(R.id.home_recyclerView)
        val numberOfColumns = 2
        recyclerView.layoutManager = GridLayoutManager(this.context, numberOfColumns)
        recyclerView.adapter =
                CustomRecyclerAdapter(noteItemList, this)


//        val textView: TextView = rootView.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val fab: FloatingActionButton = rootView.findViewById(R.id.note_fab)
        fab.setOnClickListener {

            val bundle = Bundle()
            bundle.putBoolean("isNewNote", true)
            findNavController().navigate(R.id.nav_update_note,bundle)
        }

        return rootView
    }

    private fun initializeBirdItemList(): List<NoteRecyclerViewItem>{
        val birdList  = mutableListOf<NoteRecyclerViewItem>()

        // TODO получение данных
        for (i in 0..10){
            birdList.add(NoteRecyclerViewItem("Bird №$i",
                                                    R.drawable.test,
                                                    i.toString(),
                                            "какая-то птица"))
        }

        return birdList
    }

    override fun onNoteClick(v: View?, note: NoteRecyclerViewItem) {
        Toast.makeText(v?.context, "You clicked ->"+ note.name +" with ID:"+note.NoteId, Toast.LENGTH_SHORT).show()
        // TODO Обработка нажатия на карточку

        val bundle = Bundle()
        bundle.putString("NoteID",note.NoteId)
        findNavController().navigate(R.id.action_nav_home_to_nav_note_page, bundle)
    }

}


class CustomRecyclerAdapter(private val values: List<NoteRecyclerViewItem>,
                            private val itemClickListener: OnNoteClickListener) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(){

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_home_item,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bird = values[position]
        holder.bind( bird,itemClickListener )
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var item_text : TextView? = null
        private var item_img : ImageView? = null
        init {
            item_text = itemView.findViewById(R.id.home_item_text)
            item_img = itemView.findViewById(R.id.home_item_img)
        }


        fun bind(note: NoteRecyclerViewItem, itemClickListener: OnNoteClickListener) {
            item_text?.text = note.name
            item_img?.setImageResource(note.imageId)

            itemView.setOnClickListener{
                itemClickListener.onNoteClick(it,note)
            }
        }
    }
}