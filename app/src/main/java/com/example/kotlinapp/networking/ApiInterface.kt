package com.example.kotlinapp.networking

import com.example.kotlinapp.model.CryptoCurrencies
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    //Describe the request type and relative Url

    @GET("prices?key=2e323319505fee9d82a1c85d3f49e765")
    fun getData() : Observable<List<CryptoCurrencies>>


}