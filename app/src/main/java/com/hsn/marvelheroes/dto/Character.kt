package com.hsn.marvelheroes.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hsn.marvelheroes.models.characterresponse.Comic
import com.hsn.marvelheroes.models.characterresponse.Event
import com.hsn.marvelheroes.models.characterresponse.SeriesDetails
import com.hsn.marvelheroes.models.characterresponse.Url

@Entity
data class Character(
    @PrimaryKey(autoGenerate = false)
    val characterId : String,
    val name : String,
    val description : String,
    val comics : List<Comic>,
    val series : List<SeriesDetails>,
    val events : List<Event>,
    val urls : List<Url>,
    val thumbnail: String,
    var isBookmarked : Boolean = false
)
