package com.bitopi.switchcontrol.model

import com.google.gson.annotations.SerializedName

data class OnOffResponse (
    @SerializedName("seq"   ) var seq   : Int? = null,
    @SerializedName("error" ) var error : Int? = null
)