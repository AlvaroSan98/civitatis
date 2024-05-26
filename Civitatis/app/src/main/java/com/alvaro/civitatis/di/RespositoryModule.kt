package com.alvaro.civitatis.di

import com.alvaro.civitatis.model.CharactersRepository
import com.alvaro.civitatis.model.db.CharacterDao
import com.alvaro.civitatis.model.remote.RemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RespositoryModule {

    @Provides
    @Singleton
    fun providesRepository(api: RemoteService, room: CharacterDao) = CharactersRepository(api, room)

}