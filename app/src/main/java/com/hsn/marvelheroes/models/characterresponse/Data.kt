package com.hsn.marvelheroes.models.characterresponse


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Data(
    @SerializedName("count")
    val count: String?,
    @SerializedName("limit")
    val limit: String?,
    @SerializedName("offset")
    val offset: String?,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("total")
    val total: String?
)