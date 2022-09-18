/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.data.local

import com.hsn.marvelheroes.data.local.dao.CharacterDao
import com.hsn.marvelheroes.dto.Character

class CharacterLocalRepositoryImpl(private val dao: CharacterDao) : CharacterLocalRepository {

    override suspend fun getAllCharacters(): List<Character> {
        return dao.getAllCharacters() ?: emptyList()
    }

    override suspend fun updateCharacter(character: Character) {
        dao.update(character)
    }

    override suspend fun addCharacter(character: Character) {
        dao.insert(character)
    }

    override suspend fun getCharacterById(characterId: String): Character? {
        return dao.getCharacterById(characterId)
    }

    override suspend fun getBookmarkedCharacters(): List<Character>? {
        return dao.getBookmarkedCharacters()
    }


}