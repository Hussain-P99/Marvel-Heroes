/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.data.local.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hsn.marvelheroes.models.characterresponse.Comic
import com.hsn.marvelheroes.models.characterresponse.Event
import com.hsn.marvelheroes.models.characterresponse.SeriesDetails
import com.hsn.marvelheroes.models.characterresponse.Url

class Converters {
    @TypeConverter
    fun comicsToString(comics: List<Comic>): String {
        val gson = Gson()
        return gson.toJson(comics)
    }

    @TypeConverter
    fun stringToComics(comic: String): List<Comic> {
        val typeToken = object : TypeToken<List<Comic>>() {}.type
        return Gson().fromJson(comic, typeToken)
    }

    @TypeConverter
    fun seriesToString(series: List<SeriesDetails>): String {
        val gson = Gson()
        return gson.toJson(series)
    }

    @TypeConverter
    fun stringToSeries(series: String): List<SeriesDetails> {
        val typeToken = object : TypeToken<List<SeriesDetails>>() {}.type
        return Gson().fromJson(series, typeToken)
    }

    @TypeConverter
    fun eventToString(events: List<Event>): String {
        val gson = Gson()
        return gson.toJson(events)
    }

    @TypeConverter
    fun stringToEvent(events: String): List<Event> {
        val typeToken = object : TypeToken<List<Event>>() {}.type
        return Gson().fromJson(events, typeToken)
    }


    @TypeConverter
    fun urlsToString(urls: List<Url>): String {
        val gson = Gson()
        return gson.toJson(urls)
    }

    @TypeConverter
    fun stringToUrls(urls: String): List<Url> {
        val typeToken = object : TypeToken<List<Url>>() {}.type
        return Gson().fromJson(urls, typeToken)
    }
}