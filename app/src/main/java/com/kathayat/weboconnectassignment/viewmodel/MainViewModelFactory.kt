package com.kathayat.weboconnectassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kathayat.weboconnectassignment.repository.MainRepository

class MainViewModelFactory (private val mainRepository: MainRepository): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegistationViewModel::class.java)) {
            RegistationViewModel(this.mainRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}