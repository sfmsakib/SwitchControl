package com.bitopi.switchcontrol.model

import com.google.gson.annotations.SerializedName


data class OnOffStatus (

  @SerializedName("data" ) var data : Data? = Data()

)