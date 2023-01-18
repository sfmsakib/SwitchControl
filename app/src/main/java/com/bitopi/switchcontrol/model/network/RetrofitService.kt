package com.bitopi.switchcontrol.model.network

import com.bitopi.switchcontrol.model.Movie
import com.bitopi.switchcontrol.model.ResponseModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @GET
    suspend fun turnTheLight() : Response<ResponseModel>

    @GET("movielist.json")
    suspend fun getAllMovies() : Response<List<Movie>>

    companion object{
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService{
            if (retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}