package com.example.tracingapp.data.repository.histories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tracingapp.data.model.AddHistory
import com.example.tracingapp.data.model.History
import com.example.tracingapp.network.api.HistoriesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(private val api: HistoriesApi): HistoryRepository {
    var data: MutableLiveData<List<History>> = MutableLiveData()

    // GET OWN HISTORIES
    override fun getOwnHistories(): LiveData<List<History>> {
        val call = api.getOwnHistory()

        call.enqueue(object: Callback<List<History>> {
            override fun onResponse(call: Call<List<History>>, response: Response<List<History>>) {
                if(response.isSuccessful) data.value = response.body()
            }
            override fun onFailure(call: Call<List<History>>, t: Throwable) {
                Log.e("Get Own Histories", t.message.toString())
            }
        })
        return data
    }

    // CHECK IN
    override fun checkIn(location: String, date: String, time: String): Boolean {
        val details = AddHistory(location, date, time)
        val call = api.checkIn(details)
        var status = false

        call.enqueue(object: Callback<History> {
            override fun onResponse(call: Call<History>, response: Response<History>) {
                status = response.isSuccessful
                Log.i("TAG", response.isSuccessful.toString())
            }
            override fun onFailure(call: Call<History>, t: Throwable) {
                Log.e("Add History", t.message.toString())
            }
        })
        return status
    }
}