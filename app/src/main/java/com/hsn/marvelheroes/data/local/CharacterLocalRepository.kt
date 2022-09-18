/**
 * Created by Hussain on 9/18/2022
 *
 */

package com.hsn.marvelheroes.data.local

import com.hsn.marvelheroes.dto.Character

interface CharacterLocalRepository {
    suspend fun getAllCharacters() : List<Character>
    suspend fun updateCharacter(character: Character)
    suspend fun addCharacter(character: Character)
    suspend fun getCharacterById(characterId: String): Character?
    suspend fun getBookmarkedCharacters() : List<Character>?
}