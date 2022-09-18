/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.data

import com.hsn.marvelheroes.data.local.CharacterLocalRepository
import com.hsn.marvelheroes.data.remote.CharacterRemoteRepository
import com.hsn.marvelheroes.dto.Character
import com.hsn.marvelheroes.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppRepositoryImpl(
    private val localRepository: CharacterLocalRepository,
    private val remoteRepository: CharacterRemoteRepository
) : AppRepository {


    override suspend fun getAllCharacters(shouldRefresh: Boolean): Flow<Result<List<Character>>> =
        flow {
            emit(Result.Loading())
            val characters = localRepository.getAllCharacters()
            if (shouldRefresh || characters.isEmpty()) {
                val result = remoteRepository.getCharacters()
                if (result is Result.Success) {
                    addAllCharacters(result.data ?: emptyList())
                }
                emit(result)
            } else {
                emit(Result.Success(characters))
            }
        }

    override suspend fun updateCharacter(character: Character) {
        localRepository.updateCharacter(character)
    }

    override suspend fun addCharacter(character: Character) {
        localRepository.addCharacter(character)
    }

    override suspend fun addAllCharacters(characters: List<Character>) {
        characters.forEach { character ->
            addCharacter(character)
        }
    }

    override suspend fun searchCharacter(searchText: String): Flow<Result<List<Character>>> = flow {
        emit(Result.Loading())
        val characters = localRepository.getAllCharacters()

        if (searchText.isEmpty()) {
            emit(Result.Success(characters))
            return@flow
        }

        if (characters.isEmpty()) emit(Result.Success(emptyList()))

        val foundCharacter = characters.filter { character ->
            character.name.contains(searchText, ignoreCase = true)
        }

        if (foundCharacter.isEmpty()) {
            // lookup online
            val result = remoteRepository.getCharacters(searchText)
            addAllCharacters(result.data ?: emptyList())
            emit(result)
        } else {
            emit(Result.Success(foundCharacter))
        }
    }

    override suspend fun getCharacterById(characterId: String): Flow<Result<List<Character>>> =
        flow {
            var character = localRepository.getCharacterById(characterId)
            if (character == null) {
                // search online
                val result = remoteRepository.getCharacterById(characterId)
                emit(result)
            } else {
                emit(Result.Success(listOf(character)))
            }
        }

    override suspend fun getBookmarkedCharacters(): Flow<Result<List<Character>>> = flow {
        emit(Result.Loading())
        val characters = localRepository.getBookmarkedCharacters()
        emit(Result.Success(characters))
    }

}