package com.enriselle.pokeapp.data.domain.repositoryimpl

import com.enriselle.pokeapp.data.domain.repository.PokeRepository
import com.enriselle.pokeapp.data.source.network.remote.GetPokemonByGroupResponse
import com.enriselle.pokeapp.data.source.network.remote.PokeApi
import com.enriselle.pokeapp.data.source.network.remote.PokemonInfo
import com.enriselle.pokeapp.data.source.network.resourse.Resource
import com.google.gson.Gson

class PokeRepositoryImpl(
    private val api: PokeApi,
    private val gson: Gson
): PokeRepository {
    override suspend fun getPokemonByGroup(offset: String, limit: String): Resource<GetPokemonByGroupResponse> {
        return try {
            val response = api.getPokemonByGroup(offset, limit)
            if (response.isSuccessful) {
                val loginResponse = response.body()!!
                Resource.Success(loginResponse)
            } else {
                Resource.Error("Error")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Network error")
        }
    }

    override suspend fun getPokemonByName(name: String): Resource<PokemonInfo> {
        return try {
            val response = api.getPokemonByName(name)
            if (response.isSuccessful) {
                val loginResponse = response.body()!!
                Resource.Success(loginResponse)
            } else {
                Resource.Error("Error")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Network error")
        }
    }

}