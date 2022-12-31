package com.kathayat.weboconnectassignment.repository

import com.kathayat.weboconnectassignment.network.RetrofitClient

class MainRepository() {

    suspend fun registerUser(first: String, last: String, phone: String, address: String) =
        RetrofitClient.api.registerUser(first, last, phone, address)
}