package com.enriselle.pokeapp.data.di

import com.enriselle.pokeapp.data.domain.repository.PokeRepository
import com.enriselle.pokeapp.data.domain.repositoryimpl.PokeRepositoryImpl
import com.enriselle.pokeapp.data.source.network.remote.PokeApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun providePokeApi(gson: Gson): PokeApi {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PokeApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokeRepository(api: PokeApi, gson: Gson): PokeRepository {
        return PokeRepositoryImpl(api, gson)
    }
}