package com.hsn.marvelheroes.models.characterresponse


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Result(
    @SerializedName("comics")
    val comics: Comics?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("events")
    val events: Events?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("series")
    val series: Series?,
    @SerializedName("stories")
    val stories: Stories?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("urls")
    val urls: List<Url>?
)