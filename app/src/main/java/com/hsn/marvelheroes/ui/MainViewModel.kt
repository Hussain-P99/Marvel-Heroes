/**
 * Created by Hussain on 9/17/2022
 *
 */

package com.hsn.marvelheroes.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsn.marvelheroes.data.AppRepository
import com.hsn.marvelheroes.dto.Character
import com.hsn.marvelheroes.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    init {
        getAllCharacters()
    }

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing

    val characters = mutableStateOf(listOf<Character>())

    val character = mutableStateOf<Character?>(null)

    val isLoading = mutableStateOf(false)

    val error = mutableStateOf<String?>(null)

    fun getAllCharacters(shouldRefresh: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.getAllCharacters(shouldRefresh).collect { result ->
                when(result) {
                    is Result.Error -> {
                        withContext(Dispatchers.Main) {
                            error.value = result.message
                            isLoading.value = false
                        }
                    }
                    is Result.Loading -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = true
                        }
                    }
                    is Result.Success -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            characters.value = result.data ?: emptyList()
                        }
                    }
                }
            }
        }
    }

    fun onRefresh() {
        viewModelScope.launch(Dispatchers.IO) {
            _isRefreshing.postValue(true)
            appRepository.getAllCharacters(true).collect { result ->
                when(result) {
                    is Result.Error -> {
                        withContext(Dispatchers.Main) {
                            error.value = result.message
                            isLoading.value = false
                        }
                    }
                    is Result.Loading -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = true
                        }                    }
                    is Result.Success -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            characters.value = result.data ?: emptyList()
                        }
                    }
                }
            }
            _isRefreshing.postValue(false)
        }
    }

    fun searchCharacter(searchText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.searchCharacter(searchText).collect { result ->
                when(result) {
                    is Result.Error -> {
                        withContext(Dispatchers.Main) {
                            error.value = result.message
                            isLoading.value = false
                        }
                    }
                    is Result.Loading -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = true
                        }                    }
                    is Result.Success -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            characters.value = result.data ?: emptyList()
                        }
                    }
                }
            }
        }
    }

    fun getCharacter(characterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.getCharacterById(characterId).collect { result ->
                when(result) {
                    is Result.Error -> {
                        withContext(Dispatchers.Main) {
                            error.value = result.message
                            isLoading.value = false
                        }
                    }
                    is Result.Loading -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = true
                        }                    }
                    is Result.Success -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            character.value = result.data?.firstOrNull()
                        }
                    }
                }
            }
        }
    }

    fun bookmarkCharacter(character: Character, bookmark: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            character.isBookmarked = bookmark
            appRepository.updateCharacter(character)
        }
    }

    fun getBookmarkedCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.getBookmarkedCharacters().collect { result ->
                when(result) {
                    is Result.Error -> {
                        withContext(Dispatchers.Main) {
                            error.value = result.message
                            isLoading.value = false
                        }
                    }
                    is Result.Loading -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = true
                        }                    }
                    is Result.Success -> {
                        withContext(Dispatchers.Main) {
                            isLoading.value = false
                            characters.value = result.data ?: emptyList()
                        }
                    }
                }
            }
        }
    }

    fun resetError() {
        error.value = null
    }

}