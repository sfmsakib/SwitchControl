package com.bitopi.switchcontrol.model.network

import com.bitopi.switchcontrol.model.OnOffStatus

class MainRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun turnTheLight(onOffStatus: OnOffStatus) = retrofitService.turnTheLight(onOffStatus)
    suspend fun getAllMovies() = retrofitService.getAllMovies()

}