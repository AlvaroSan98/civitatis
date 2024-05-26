package com.alvaro.civitatis.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.civitatis.model.CharactersRepository
import com.alvaro.civitatis.model.db.CharacterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharactersRepository
): ViewModel() {

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading
    private val _character = MutableLiveData<CharacterEntity>()
    val character: LiveData<CharacterEntity> get() = _character

    fun getCharacterDetails(id: String) {
        viewModelScope.launch {
            val character = repository.getCharacterById(id)
            _character.value = character
            _loading.value = false
        }
    }

}