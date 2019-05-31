package com.example.kotlinapp.networking

import com.example.kotlinapp.BuildConfig
import com.example.kotlinapp.model.CryptoCurrencies
import com.example.kotlinapp.model.Interval
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    //Describe the request type and relative Url

    val apiKey : String
        get() = BuildConfig.ApiKey

    @GET("prices?key=" )
    fun getData() : Observable<List<CryptoCurrencies>>

    @GET("currencies/interval?key= ")
    fun getMoreData():Observable<List<Interval>>



}