package com.example.android.navigationadvancedsample.homescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val description = MutableLiveData<String>()

    init {
        if (description.value == null) {

            description.postValue("aaaaa")
        }
    }

    fun loadData() {
        description.postValue("aaaaa")
    }
}