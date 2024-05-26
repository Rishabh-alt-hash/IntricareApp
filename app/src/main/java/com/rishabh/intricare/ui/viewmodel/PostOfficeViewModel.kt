package com.rishabh.intricare.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rishabh.intricare.data.model.PostOffice
import com.rishabh.intricare.data.repository.PostOfficeRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class PostOfficeViewModel(private val repository: PostOfficeRepository) : ViewModel() {

    val allPostOffices: LiveData<List<PostOffice>> = repository.allPostOffices

   fun fetchPostOffices(city: String) {
       viewModelScope.launch {
           repository.fetchPostOfficesFormApi(city)
       }
   }

    fun getLocalPostOffices() {
        // This method can remain empty as LiveData will automatically fetch data from Room DB
    }
}

class PostOfficeViewModelFactory(private val repository: PostOfficeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostOfficeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostOfficeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}