package com.alvaro.civitatis.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.alvaro.civitatis.model.db.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    private val CHARACTERS_DATABASE_NAME = "characters_database"

    @Singleton
    @Provides
    fun provideCharacterDatabase(application: Application): CharacterDatabase {
        return Room.databaseBuilder(application, CharacterDatabase::class.java, CHARACTERS_DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideCharactersDao(db: CharacterDatabase) = db.getCharactersDao()

}