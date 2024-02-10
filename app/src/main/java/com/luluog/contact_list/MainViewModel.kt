package com.luluog.contact_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luluog.contact_list.db.repository.ContactListRepository
import com.luluog.contact_list.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ContactListRepository) : ViewModel() {

    val newName = MutableLiveData<String>()
    val newPhone = MutableLiveData<String>()
    val newEmail = MutableLiveData<String>()


    init {
        newName.value = ""
        newPhone.value = ""
        newEmail.value = ""
    }


    val allContacts = MutableLiveData<List<Contact>>()

    fun getAllContacts() {
        viewModelScope.launch(Dispatchers.IO){
            allContacts.postValue(repository.getAllContacts())
        }
    }


    fun upsertContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) { repository.upsertContact(contact) }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO){ repository.deleteContact(contact) }
    }
}