package com.alvaro.civitatis.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CharacterDao {

    @Upsert()
    suspend fun insertCharacter(characterEntity: CharacterEntity)

    @Query("SELECT * FROM Character WHERE id = :id")
    suspend fun getCharacterById(id: String): CharacterEntity

}