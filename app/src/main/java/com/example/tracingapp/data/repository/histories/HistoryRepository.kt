package com.example.tracingapp.data.repository.histories

import androidx.lifecycle.LiveData
import com.example.tracingapp.data.model.History

interface HistoryRepository {
    fun getOwnHistories(): LiveData<List<History>>
    fun checkIn(location: String, date: String, time: String): Boolean
}