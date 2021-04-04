package com.example.adfmp1h21_bird.ui.base

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        viewModel = ViewModelProvider(this).get(BaseViewModel::class.java)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.base_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = CustomRecyclerAdapter(getDataList())

        return rootView
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//
//
////        val textView: TextView = root.findViewById(R.id.text_gallery)
////        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = it
////        })
//    }
    
    private fun getDataList(): List<String> {
        val data:List<String> = context?.resources?.getStringArray(R.array.temp_db)?.toList() ?: emptyList()
        return data
    }


}

class CustomRecyclerAdapter(private val values: List<String>) :
        RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.recyclerview_base_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.base_text?.text = values[position]
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var base_text: TextView? = null
        init {
            base_text = itemView?.findViewById(R.id.base_text)
        }
    }
}