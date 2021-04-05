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

class HomeFragment : Fragment(), OnBirdClickListener {

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
                CustomRecyclerAdapter(birdItemList, this)


//        val textView: TextView = rootView.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val fab: FloatingActionButton = rootView.findViewById(R.id.home_fab)
        fab.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.nave_create_page)
        }

        return rootView
    }

    private fun initializeBirdItemList(): List<BirdRecyclerViewItem>{
        val birdList  = mutableListOf<BirdRecyclerViewItem>()

        for (i in 0..10){
            birdList.add(BirdRecyclerViewItem("Bird №$i",
                                                    R.drawable.test,
                                                    i,
                                            "какая-то птица"))
        }

        return birdList
    }

    override fun onBirdClick(v: View?, bird: BirdRecyclerViewItem) {
        Toast.makeText(v?.context, "You clicked ->"+ bird.name +" with ID:"+bird.birdId, Toast.LENGTH_SHORT).show()
        // TODO Обработка нажатия на карточку
    }

}


class CustomRecyclerAdapter(private val values: List<BirdRecyclerViewItem>,
                            private val itemClickListener: OnBirdClickListener) :
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
        var item_text : TextView? = null
        var item_img : ImageView? = null
        init {
            item_text = itemView.findViewById(R.id.home_item_text)
            item_img = itemView.findViewById(R.id.home_item_img)
        }


        fun bind(bird: BirdRecyclerViewItem, itemClickListener: OnBirdClickListener) {
            item_text?.text = bird.name
            item_img?.setImageResource(bird.birdImageId)

            itemView.setOnClickListener{
                itemClickListener.onBirdClick(it,bird)
            }
        }
    }
}