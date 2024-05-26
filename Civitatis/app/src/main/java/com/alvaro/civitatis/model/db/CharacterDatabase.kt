package com.alvaro.civitatis.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = true
)
abstract class CharacterDatabase: RoomDatabase() {

    abstract fun getCharactersDao(): CharacterDao

}