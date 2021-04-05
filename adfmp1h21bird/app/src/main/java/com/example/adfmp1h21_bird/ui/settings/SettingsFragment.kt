package com.example.adfmp1h21_bird.ui.settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.lifecycle.Observer
import com.example.adfmp1h21_bird.R

class SettingsFragment : Fragment(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

//    companion object {
//        fun newInstance() = SettingsFragment()
//    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView:View = inflater.inflate(R.layout.fragment_settings, container, false)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

//        val textView: TextView = rootView.findViewById(R.id.setting_text)
//        viewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val clear_btn:Button = rootView.findViewById(R.id.settings_btn_clear_statistics)
        clear_btn.setOnClickListener(this)

        val save_btn:Button = rootView.findViewById(R.id.settings_btn_save)
        save_btn.setOnClickListener(this)

        val switch_geotag: SwitchCompat = rootView.findViewById(R.id.settings_switch_geotag)
        switch_geotag.setOnCheckedChangeListener(this)

        return rootView
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
////         TODO: Use the ViewModel
//    }


    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.settings_btn_clear_statistics ->{
                    Toast.makeText(context, "Clear statistics!",Toast.LENGTH_SHORT).show()
                }

                R.id.settings_btn_save ->{
                    Toast.makeText(context, "Save settings!",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (buttonView != null) {
            when(buttonView.id){
                R.id.settings_switch_geotag ->{
                    if(isChecked){
                        Toast.makeText(context, "Auto geotag ON!",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "Auto geotag OFF!",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}

