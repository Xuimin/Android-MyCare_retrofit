package com.example.tracingapp.data.repository.users

import androidx.lifecycle.LiveData
import com.example.tracingapp.data.model.User

interface UserRepository {
    fun register(fullname: String, phone: String, location: String, password: String, confirmPassword: String): LiveData<Boolean>
    fun login(phone: String, password: String): LiveData<Boolean>
    fun getOwnDetails(): LiveData<User>
    fun updateDetails(fullname: String, ic: String, location: String, password: String, confirmPassword: String): LiveData<Boolean>
    fun logout(): Boolean
}