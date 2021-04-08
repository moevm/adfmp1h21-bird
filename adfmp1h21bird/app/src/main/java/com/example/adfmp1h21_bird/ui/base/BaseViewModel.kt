package com.example.adfmp1h21_bird.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Data Fragment"
    }
    val text: LiveData<String> = _text
}