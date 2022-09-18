package com.hsn.marvelheroes.di

import android.content.Context
import com.hsn.marvelheroes.data.AppRepository
import com.hsn.marvelheroes.data.AppRepositoryImpl
import com.hsn.marvelheroes.data.local.CharacterLocalRepository
import com.hsn.marvelheroes.data.local.CharacterLocalRepositoryImpl
import com.hsn.marvelheroes.data.local.db.AppDatabase
import com.hsn.marvelheroes.data.remote.CharacterRemoteRepository
import com.hsn.marvelheroes.data.remote.CharacterRemoteRepositoryImpl
import com.hsn.marvelheroes.network.MarvelApi
import com.hsn.marvelheroes.network.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideAppRepository(
        characterLocalSource: CharacterLocalRepository,
        characterRemoteRepository: CharacterRemoteRepository
    ) : AppRepository {
        return AppRepositoryImpl(characterLocalSource,characterRemoteRepository)
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideLocalDataRepository(appDatabase: AppDatabase) : CharacterLocalRepository {
        return CharacterLocalRepositoryImpl(appDatabase.characterDao)
    }

    @Provides
    @Singleton
    fun marvelApi() : MarvelApi {
        return RetrofitBuilder.marvelsApi
    }

    @Provides
    @Singleton
    fun remoteRepository(marvelApi: MarvelApi) : CharacterRemoteRepository {
        return CharacterRemoteRepositoryImpl(marvelApi)
    }
}