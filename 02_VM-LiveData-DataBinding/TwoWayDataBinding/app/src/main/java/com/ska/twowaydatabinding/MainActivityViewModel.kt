package com.ska.twowaydatabinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    val userName = MutableLiveData<String>()

    init {
        userName.postValue("Frank")
    }
}