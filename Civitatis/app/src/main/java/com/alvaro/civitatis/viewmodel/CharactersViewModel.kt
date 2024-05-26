package com.alvaro.civitatis.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.civitatis.model.CharactersRepository
import com.alvaro.civitatis.model.DomainCharacter
import com.alvaro.civitatis.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
): ViewModel() {

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading

    private val _comics = MutableLiveData<List<DomainCharacter>>(emptyList())
    val comics: LiveData<List<DomainCharacter>> get() = _comics

    init {
        viewModelScope.launch {
            _comics.value = repository.getCharactersFromApi()
            _loading.value = false
        }
    }

    fun getCharactersOrdered(type: String, ascending: Boolean) {
        _loading.value = true

        var orderedBy = when (type) {
            Constants.NAME ->
                if (ascending) {
                    Constants.NAME
                } else {
                    "-${Constants.NAME}"
                }
            Constants.MODIFIED -> {
                if (ascending) {
                    Constants.MODIFIED
                } else {
                    "-${Constants.MODIFIED}"
                }
            }
            else -> "error"
        }

        viewModelScope.launch {
            val characters = repository.getCharactersOrderedFromApi(orderedBy)
            _comics.value = characters
            _loading.value = false
        }
    }
}