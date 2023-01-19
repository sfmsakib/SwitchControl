package com.bitopi.switchcontrol.model

import com.google.gson.annotations.SerializedName


data class Switches (

  @SerializedName("switch" ) var switch : String? = null,
  @SerializedName("outlet" ) var outlet : Int?    = null

)