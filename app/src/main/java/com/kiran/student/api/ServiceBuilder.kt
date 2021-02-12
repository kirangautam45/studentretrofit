package com.kiran.student.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val BASE_URL = "http://10.0.2.2:3000/api/v1/"
    var token:String?=null
    private val okhttp =
    OkHttpClient.Builder()

    //create retrofit builder
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttp.build())

    //Create retrofit instance
    private val retrofit = retrofitBuilder.build()
    //Generic function
    fun<T> buildService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }
}