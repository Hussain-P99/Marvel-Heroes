package com.hsn.marvelheroes.models.characterresponse


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.hsn.marvelheroes.dto.Character

@Keep
data class CharacterResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String?,
    @SerializedName("attributionText")
    val attributionText: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("status")
    val status: String?
)

fun CharacterResponse.toDto(): List<Character> {
    return this.data?.results?.map { character ->
        Character(
            character.id ?: "",
            character.name ?: "",
            character.description ?: "",
            character.comics?.items ?: emptyList(),
            character.series?.items ?: emptyList(),
            character.events?.items ?: emptyList(),
            character.urls ?: emptyList(),
            character.thumbnail?.path + "." + character.thumbnail?.extension
        )
    } ?: emptyList()
}