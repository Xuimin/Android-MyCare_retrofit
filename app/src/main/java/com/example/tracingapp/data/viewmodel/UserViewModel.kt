package com.example.tracingapp.data.viewmodel

import androidx.lifecycle.*
import com.example.tracingapp.data.model.User
import com.example.tracingapp.data.repository.users.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repositoryImpl: UserRepositoryImpl): ViewModel() {

    // REGISTER
    fun register(fullname: String, phone: String, location: String, password: String, confirmPassword: String): LiveData<Boolean> {
        return repositoryImpl.register(fullname, phone, location, password, confirmPassword)
    }

    // LOGIN
    fun login(phone: String, password: String): LiveData<Boolean> {
        return repositoryImpl.login(phone, password)
    }

    // GET OWN DETAILS
    val getOwnDetails: LiveData<User> = repositoryImpl.getOwnDetails()

    // UPDATE OWN DETAILS
    fun updateOwnDetails(fullname: String, ic: String, location: String, password: String, confirmPassword: String): LiveData<Boolean> {
        return repositoryImpl.updateDetails(fullname, ic, location, password, confirmPassword)
    }

    // LOGOUT
    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.logout()
        }
    }
}

