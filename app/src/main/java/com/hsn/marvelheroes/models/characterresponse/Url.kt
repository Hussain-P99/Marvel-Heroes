package com.hsn.marvelheroes.models.characterresponse


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Url(
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)