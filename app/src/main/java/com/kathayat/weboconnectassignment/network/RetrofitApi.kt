package com.kathayat.weboconnectassignment.network

import com.kathayat.weboconnectassignment.dto.UserDetails
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface RetrofitApi {

    @POST("register-user")
    suspend fun registerUser(
        @Field("firstName") firstname:String,
        @Field("lastName") lastname:String,
        @Field("phone") phone:String,
        @Field("postcode") postcode:String
    ):Response<UserDetails>
}