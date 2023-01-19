package com.bitopi.switchcontrol.model

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("switches" ) var switches : ArrayList<Switches> = arrayListOf()

)