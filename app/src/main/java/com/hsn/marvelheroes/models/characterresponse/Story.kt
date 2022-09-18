package com.hsn.marvelheroes.models.characterresponse


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Story(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("type")
    val type: String?
)