package com.bitopi.switchcontrol.model

import com.bitopi.switchcontrol.model.DataModel
import com.google.gson.annotations.SerializedName

data class ResponseModel (
    @SerializedName("deviceId")
    var deviceId : String? = null,
    @SerializedName("data")
    var data : DataModel? = DataModel()
)