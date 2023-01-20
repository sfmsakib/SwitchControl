package com.bitopi.switchcontrol.model.network

import com.bitopi.switchcontrol.model.Movie
import com.bitopi.switchcontrol.model.OnOffResponse
import com.bitopi.switchcontrol.model.OnOffStatus
import com.bitopi.switchcontrol.model.ResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {

    @POST("zeroconf/switches")
    suspend fun turnTheLight(@Body onOffStatus: OnOffStatus) : Response<OnOffResponse>

    @GET("movielist.json")
    suspend fun getAllMovies() : Response<List<Movie>>

    companion object{
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService{
            if (retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.12.208:8081/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}