package com.bitopi.switchcontrol.model.network

class MainRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun turnTheLight() = retrofitService.turnTheLight()
    suspend fun getAllMovies() = retrofitService.getAllMovies()

}