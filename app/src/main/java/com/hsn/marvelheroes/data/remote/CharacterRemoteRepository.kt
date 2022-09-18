/**
 * Created by Hussain on 9/18/2022
 *
 */

package com.hsn.marvelheroes.data.remote

import com.hsn.marvelheroes.dto.Character
import com.hsn.marvelheroes.utils.Result

interface CharacterRemoteRepository {
    suspend fun getCharacters(searchText: String?= null): Result<List<Character>>
    suspend fun getCharacterById(characterId: String): Result<List<Character>>
}