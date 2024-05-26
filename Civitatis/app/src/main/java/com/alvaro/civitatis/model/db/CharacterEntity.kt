package com.alvaro.civitatis.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Character")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("image") val imageUrl: String,
    @ColumnInfo("comics") val comics: String,
    @ColumnInfo("series") val series: String,
    @ColumnInfo("stories") val stories: String,
    @ColumnInfo("urls") val urls: String,
)