package com.sk.roomdemo

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sk.roomdemo.db.Subscriber
import com.sk.roomdemo.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val subscriberRepository: SubscriberRepository) : ViewModel(), Observable {

    val subscribers = subscriberRepository.subscribers
    private var isUpdateOrDelete = false;
    private lateinit var subscriberToUpdateOrDelete: Subscriber
    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage
    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if(inputName.value == null){
            statusMessage.value = Event("Please enter a subscriber name")
        } else if(inputEmail.value == null){
            statusMessage.value = Event("Please enter a subscriber email")
        } else {
            if(isUpdateOrDelete){
                subscriberToUpdateOrDelete.name = inputName.value!!
                subscriberToUpdateOrDelete.email = inputEmail.value!!
                update(subscriberToUpdateOrDelete)
            } else {
                insert(Subscriber(0, inputName.value!!, inputEmail.value!!))
                inputName.value = null
                inputEmail.value = null
            }
        }

    }

    fun clearAllOrDelete() {
        if(isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)
        } else {
            deleteAll()

        }
    }

    private fun insert(subscriber: Subscriber) {
        viewModelScope.launch {
            val insertedRowCount = subscriberRepository.insertSubscriber(subscriber)
            if(insertedRowCount > -1){
                statusMessage.value = Event("Subscriber inserted successfully")
            } else {
                statusMessage.value = Event("Error occured while inserting a new subscriber")
            }
        }
    }

    private fun update(subscriber: Subscriber) {
        viewModelScope.launch {
            val updatedRowCount = subscriberRepository.updateSubscriber(subscriber)
            if(updatedRowCount > 0){
                inputName.value = null
                inputEmail.value = null
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("$updatedRowCount Row updated successfully")
            } else {
                statusMessage.value = Event("Error occured while updating subscriber")
            }

        }
    }

    private fun delete(subscriber: Subscriber) {
        viewModelScope.launch {
            val deletedRowCount = subscriberRepository.deleteSubscriber(subscriber)
            if(deletedRowCount > 0){
                inputName.value = null
                inputEmail.value = null
                isUpdateOrDelete = false
                saveOrUpdateButtonText.value = "Save"
                clearAllOrDeleteButtonText.value = "Clear All"
                statusMessage.value = Event("$deletedRowCount Row deleted successfully")
            } else {
                statusMessage.value = Event("Error occured while deleting subscriber")
            }

        }
    }

    private fun deleteAll() {
        viewModelScope.launch {
            val deletedRowCount = subscriberRepository.deleteAllSubscribers()
            if(deletedRowCount > 0){
                statusMessage.value = Event("$deletedRowCount Row deleted successfully")
            } else {
                statusMessage.value = Event("Error occured while deleting subscribers")
            }
        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}