package com.glee.dashboard.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is orders Fragment"
    }
    val text: LiveData<String> = _text
}