/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.data.remote

import com.hsn.marvelheroes.dto.Character
import com.hsn.marvelheroes.models.characterresponse.toDto
import com.hsn.marvelheroes.network.MarvelApi
import com.hsn.marvelheroes.utils.Constants
import com.hsn.marvelheroes.utils.Result
import com.hsn.marvelheroes.utils.Utils

class CharacterRemoteRepositoryImpl(private val marvelApi: MarvelApi) : CharacterRemoteRepository {

    override suspend fun getCharacters(searchText: String?): Result<List<Character>> {
        val ts = System.currentTimeMillis()
        val hash = Utils.calculateMd5Sum(ts.toString(),Constants.PUB_KEY,Constants.PRIV_KEY)
        try {
            val result =
                marvelApi.getCharacters(ts.toString(), Constants.PUB_KEY, hash, search = searchText)
            return Result.Success(data = result?.toDto() ?: emptyList())
        } catch (e : Exception) {
            return Result.Error(e.localizedMessage ?: "Something Went Wrong")
        }
    }

    override suspend fun getCharacterById(characterId: String): Result<List<Character>> {
        val ts = System.currentTimeMillis()
        val hash = Utils.calculateMd5Sum(ts.toString(),Constants.PUB_KEY,Constants.PRIV_KEY)
        try {
            val result = marvelApi.getCharacter(characterId.toInt())
            return Result.Success(data = result?.toDto() ?: emptyList())
        } catch (e : Exception) {
            return Result.Error(e.localizedMessage ?: "Something Went Wrong")
        }
    }
}