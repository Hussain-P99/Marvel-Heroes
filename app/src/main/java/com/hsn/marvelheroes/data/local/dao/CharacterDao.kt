/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.data.local.dao

import androidx.room.*
import com.hsn.marvelheroes.dto.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: Character)

    @Update
    suspend fun update(character: Character)

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters() : List<Character>?

    @Query("SELECT * FROM character WHERE characterId = :id")
    suspend fun getCharacterById(id : String) : Character?

    @Query("SELECT * FROM character WHERE isBookmarked == 1")
    suspend fun getBookmarkedCharacters() : List<Character>?
}