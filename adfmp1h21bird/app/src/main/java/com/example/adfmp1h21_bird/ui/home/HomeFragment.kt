package com.example.adfmp1h21_bird.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adfmp1h21_bird.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var birdItemList: List<BirdRecyclerViewItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        birdItemList = initializeBirdItemList()

        val recyclerView: RecyclerView = rootView.findViewById(R.id.home_recyclerView)
        val numberOfColumns = 2
        recyclerView.layoutManager = GridLayoutManager(this.context, numberOfColumns)
        recyclerView.adapter =
                CustomRecyclerAdapter(birdItemList)


//        val textView: TextView = rootView.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val fab: FloatingActionButton = rootView.findViewById(R.id.home_fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        return rootView
    }

    private fun getDataList(): List<String> {
        val data:List<String> = context?.resources?.getStringArray(R.array.temp_db)?.toList() ?: emptyList()
        return data
    }

    private fun initializeBirdItemList(): List<BirdRecyclerViewItem>{
        val birdList  = mutableListOf<BirdRecyclerViewItem>()

        for (i in 0..10){
            birdList.add(BirdRecyclerViewItem("Bird №$i", R.drawable.test, i))
        }

        return birdList
    }

}


class CustomRecyclerAdapter(private val values: List<BirdRecyclerViewItem>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

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
        holder.item_text?.text = values[position].name
        holder.item_img?.setImageResource(values[position].birdImageId)
//        holder.item_id?.text = values[position].birdId.toString()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item_text : TextView? = null
        var item_img : ImageView? = null
        var item_id: TextView? = null
        init {
            item_text = itemView.findViewById(R.id.home_item_text)
            item_img = itemView.findViewById(R.id.home_item_img)
//            item_id = itemView.findViewById(R.id.home_item_id)
        }
    }
}