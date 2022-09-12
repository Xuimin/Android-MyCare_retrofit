package com.example.tracingapp.data.repository.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tracingapp.data.model.*
import com.example.tracingapp.network.api.UsersApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UsersApi): UserRepository {
    var data: MutableLiveData<User> = MutableLiveData()

    // REGISTER
    override fun register(fullname: String, phone: String, location: String, password: String, confirmPassword: String): LiveData<Boolean> {
        val details = UserDetails(fullname, phone, location, password, confirmPassword)
        val call = api.register(details)
        var status: MutableLiveData<Boolean> = MutableLiveData()

        call.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                status.value = response.isSuccessful
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Register", t.message.toString())
            }
        })
        return status
    }

    // LOGIN
    override fun login(phone: String, password: String): LiveData<Boolean> {
        val details = Login(phone, password)
        val call = api.login(details)
        var status: MutableLiveData<Boolean> = MutableLiveData()

        call.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                status.value = response.isSuccessful
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Login", t.message.toString())
            }
        })
        return status
    }

    // GET OWN DETAILS
    override fun getOwnDetails(): LiveData<User> {
        val call = api.getOwnDetails()

        call.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) data.value = response.body()
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Get Own Details", t.message.toString())
            }
        })
        return data
    }

    // UPDATE OWN DETAILS
    override fun updateDetails(fullname: String, ic: String, location: String, password: String, confirmPassword: String): LiveData<Boolean> {
        val details = UpdateDetails(fullname, ic, location, password, confirmPassword)
        val call = api.updateUserDetails(details)
        var status: MutableLiveData<Boolean> = MutableLiveData()

        call.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                status.value = response.isSuccessful
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Update Own Details", t.message.toString())
            }
        })
        return status
    }

    // LOGOUT
    override fun logout(): Boolean {
        val call = api.logout()
        var status = false

        call.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                status = response.isSuccessful
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Login", t.message.toString())
            }
        })
        return status
    }
}