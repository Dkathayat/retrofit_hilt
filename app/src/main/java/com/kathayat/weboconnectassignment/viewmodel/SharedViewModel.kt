package com.kathayat.weboconnectassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
    private val _selectedOtp = MutableLiveData<String>()
    val selectedOtp:LiveData<String> get() = _selectedOtp


    fun shareOtp(otp:String){
        _selectedOtp.value = otp
    }
}