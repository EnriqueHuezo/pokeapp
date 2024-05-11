package com.enriselle.pokeapp.data.source.network.remote


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Get all pokemon
data class GetPokemonByGroupResponse(
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String
)

// Get only one pokemon
@Parcelize
data class PokemonInfo(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val height: Int,
    val weight: Int,
    val abilities: List<DataAbilities>,
    val types: List<DataType>,
    val stats: List<DataStats>
): Parcelable

@Parcelize
data class Sprites(
    val front_default: String,
    val front_shiny: String
): Parcelable

@Parcelize
data class DataType(
    val type: Type
): Parcelable

@Parcelize
data class Type(
    val name: String
): Parcelable

@Parcelize
data class DataStats(
    val base_stat: Int,
    val stat: Stat
): Parcelable

@Parcelize
data class Stat(
    val name: String
): Parcelable

@Parcelize
data class DataAbilities(
    val ability: Ability
): Parcelable

@Parcelize
data class Ability(
    val name: String
): Parcelable

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemonByGroup(
        @Query("offset") offset: String,
        @Query("limit") limit: String
    ): Response<GetPokemonByGroupResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): Response<PokemonInfo>
}