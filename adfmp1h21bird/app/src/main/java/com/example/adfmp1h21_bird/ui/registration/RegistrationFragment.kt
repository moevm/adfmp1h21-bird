package com.example.adfmp1h21_bird.ui.registration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.adfmp1h21_bird.R

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView:View = inflater.inflate(R.layout.fragment_registration, container, false)

        val username = rootView.findViewById<EditText>(R.id.signup_username)
        val password = rootView.findViewById<EditText>(R.id.signup_password)
        val register = rootView.findViewById<Button>(R.id.confirmSignUp)
        val loading = rootView.findViewById<ProgressBar>(R.id.loading)

        register.setOnClickListener {

            Toast.makeText(context, "Rigister user!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registrationFragment_to_nav_login)

        }


        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}