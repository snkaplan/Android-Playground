package com.ska.viewmodelscopedemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.ska.viewmodelscopedemo.data.User
import com.ska.viewmodelscopedemo.data.UserRepository
import kotlinx.coroutines.*

class MainActivityViewModel: ViewModel() {
    private val usersRepository = UserRepository()
    init {
        viewModelScope.launch {
            getUserData()
        }
    }

    val usersWithBuilder = liveData(Dispatchers.IO) {
        val result = usersRepository.getUsers()
        emit(result)
    }


    var users: MutableLiveData<List<User>> = MutableLiveData()
    /**
     * Above we used custom CoroutineScope and custom Job in view model and as a best practice we cancelled job onCleared
     */
/*    private val myJob = Job()
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)
    fun getUserData(){
        myScope.launch {

        }
    }
    override fun onCleared() {
        myJob.cancel()
        super.onCleared()
    }*/

    /**
     * Above we used viewModelScope from kotlin extension and it automatically cancels job onCleared
     */

    private fun getUserData(){
        // viewModelScope guarantees the wait all jobs
        viewModelScope.launch {
            var result: List<User>? = null
            // Switch scope of coroutine
            withContext(Dispatchers.IO){
                result = usersRepository.getUsers()
            }
            users.postValue(result)
        }
    }
}