package com.kathayat.weboconnectassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kathayat.weboconnectassignment.dto.UserDetails
import com.kathayat.weboconnectassignment.network.NetworkResource
import com.kathayat.weboconnectassignment.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RegistationViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _userResponse: MutableLiveData<NetworkResource<UserDetails>> = MutableLiveData()
    val userResponse: MutableLiveData<NetworkResource<UserDetails>> get() = _userResponse


    fun registerUser(first: String, last: String, phone: String, address: String) =
        viewModelScope.launch {
            _userResponse.postValue(NetworkResource.Loading())
            val response = mainRepository.registerUser(first, last, phone, address)
            _userResponse.postValue(handleRegistertionResponse(response))
        }

    private fun handleRegistertionResponse(response: Response<UserDetails>): NetworkResource<UserDetails> {
        if (response.isSuccessful) {
            response.body()?.let {
                return NetworkResource.Success(it)
            }
        }
        return NetworkResource.Error(response.message())
    }
}