package com.ska.viewmodelfactory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int) : ViewModel() {
    private var total = MutableLiveData<Int>()
    val totalData: LiveData<Int>
        get() = total

    init {
        total.postValue(startingTotal)
    }

    fun getTotal(): Int? {
        return total.value
    }

    fun setTotal(input:Int){
        total.postValue(total.value?.plus(input))
    }
}