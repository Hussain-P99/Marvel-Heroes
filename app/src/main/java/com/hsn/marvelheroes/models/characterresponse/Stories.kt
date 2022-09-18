package com.hsn.marvelheroes.models.characterresponse


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Stories(
    @SerializedName("available")
    val available: String?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<Story>?,
    @SerializedName("returned")
    val returned: String?
)