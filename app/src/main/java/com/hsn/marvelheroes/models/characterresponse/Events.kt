package com.hsn.marvelheroes.models.characterresponse


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Events(
    @SerializedName("available")
    val available: String?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<Event>?,
    @SerializedName("returned")
    val returned: String?
)