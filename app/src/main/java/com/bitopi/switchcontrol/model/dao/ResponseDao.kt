package com.bitopi.switchcontrol.model.dao

import com.bitopi.switchcontrol.model.ResponseModel
import retrofit2.Response
import retrofit2.http.POST

interface ResponseDao {
    @POST
    suspend fun turnTheLight() : Response<ResponseModel>
}