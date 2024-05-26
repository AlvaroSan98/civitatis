package com.alvaro.civitatis.model

import androidx.lifecycle.map
import com.alvaro.civitatis.model.db.CharacterDao
import com.alvaro.civitatis.model.db.CharacterEntity
import com.alvaro.civitatis.model.remote.RemoteService
import toDomainCharacter
import toEntityCharacter
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val api: RemoteService,
    private val room: CharacterDao
) {

    suspend fun getCharactersFromApi(): List<DomainCharacter> {
        val charactersResponse = api.fetchCharacters()
        val domainCharactersList = charactersResponse.body()?.data?.results?.map { it.toDomainCharacter() }
        val dbCharactersList = charactersResponse.body()?.data?.results?.map { it.toEntityCharacter() }
        dbCharactersList?.forEach {
            room.insertCharacter(it)
        }
        return domainCharactersList!!
    }

    suspend fun getCharactersOrderedFromApi(orderedBy: String): List<DomainCharacter> {
        val characterResponse = api.fetchCharactersOrdered(orderedBy)
        val domainCharactersList = characterResponse.body()?.data?.results?.map { it.toDomainCharacter() }
        val dbCharactersList = characterResponse.body()?.data?.results?.map { it.toEntityCharacter() }
        dbCharactersList?.forEach {
            room.insertCharacter(it)
        }
        return domainCharactersList!!
    }

    suspend fun getCharacterById(id: String): CharacterEntity {
        val character = room.getCharacterById(id)
        return character!!
    }

}