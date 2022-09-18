/**
 * Created by Hussain on 9/18/2022
 *
 */

package com.hsn.marvelheroes.data

import com.hsn.marvelheroes.dto.Character
import com.hsn.marvelheroes.utils.Result
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    suspend fun getAllCharacters(shouldRefresh: Boolean = false): Flow<Result<List<Character>>>
    suspend fun updateCharacter(character: Character)
    suspend fun addCharacter(character: Character)
    suspend fun addAllCharacters(characters: List<Character>)
    suspend fun searchCharacter(searchText: String): Flow<Result<List<Character>>>
    suspend fun getCharacterById(characterId: String): Flow<Result<List<Character>>>
    suspend fun getBookmarkedCharacters(): Flow<Result<List<Character>>>
}