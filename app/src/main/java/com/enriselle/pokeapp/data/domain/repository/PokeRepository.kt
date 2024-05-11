package com.enriselle.pokeapp.data.domain.repository

import com.enriselle.pokeapp.data.source.network.remote.GetPokemonByGroupResponse
import com.enriselle.pokeapp.data.source.network.remote.PokemonInfo
import com.enriselle.pokeapp.data.source.network.resourse.Resource

interface PokeRepository {
    suspend fun getPokemonByGroup(offset: String, limit: String): Resource<GetPokemonByGroupResponse>
    suspend fun getPokemonByName(name: String): Resource<PokemonInfo>
}