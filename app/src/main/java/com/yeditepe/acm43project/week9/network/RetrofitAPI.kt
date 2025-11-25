package com.yeditepe.acm43project.week9.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Client{

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val service= retrofit.create(FakeStoreAPIService::class.java)
}


