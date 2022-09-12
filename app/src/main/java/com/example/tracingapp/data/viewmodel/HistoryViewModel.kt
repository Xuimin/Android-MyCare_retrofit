package com.example.tracingapp.data.viewmodel

import androidx.lifecycle.*
import com.example.tracingapp.data.model.History
import com.example.tracingapp.data.repository.histories.HistoryRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repositoryImpl: HistoryRepositoryImpl): ViewModel() {

    // GET OWN HISTORIES
    val getOwnHistories: LiveData<List<History>> = repositoryImpl.getOwnHistories()

    // CHECK IN
    fun checkIn(location: String, date: String, time: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.checkIn(location, date, time)
        }
    }
}
