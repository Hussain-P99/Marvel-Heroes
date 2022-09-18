/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.network

import com.hsn.marvelheroes.models.characterresponse.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = 100,
        @Query("offset") offset : Int = 0,
        @Query("nameStartsWith") search : String? = null
    ): CharacterResponse?

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: Int) : CharacterResponse?
}